package com.jobApplication.generateMail.Service;

import com.jobApplication.generateMail.Model.Recruiter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import technology.tabula.ObjectExtractor;
import technology.tabula.Page;
import technology.tabula.RectangularTextContainer;
import technology.tabula.Table;
import technology.tabula.extractors.SpreadsheetExtractionAlgorithm;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecruiterPdfExtractorService {

    @Value("${pdf.filePath}")
    private String filePath;

    public List<Recruiter> extractRecruiterFromPdf() {
        List<Recruiter> recruiters = new ArrayList<>();

        try{
            File file = new File(filePath);
            PDDocument document = PDDocument.load(file);
            ObjectExtractor extractor = new ObjectExtractor(document);
            SpreadsheetExtractionAlgorithm sea = new SpreadsheetExtractionAlgorithm();

            for(int page = 1 ; page < document.getNumberOfPages() ; page++) {
                Page pdfPage = extractor.extract(page);

                List<Table> tables = sea.extract(pdfPage);

                for (Table table : tables) {
                    List<List<RectangularTextContainer>> rows = table.getRows();

                    // Skip header row
                    for (int i = 1; i < rows.size(); i++) {
                        List<RectangularTextContainer> row = rows.get(i);

                        Recruiter recruiter = new Recruiter(
                                safe(row, 1),   // Name
                                safe(row, 2),   // Email
                                safe(row, 4)    // Company
                        );
                        recruiters.add(recruiter);
                    }
                }
            }

            document.close();

        } catch(Exception e) {
            throw new RuntimeException("Something went wrong while extracting recruiters");
        }

        return recruiters;
    }

    private String safe(List<RectangularTextContainer> row, int index) {
        return index < row.size() ? row.get(index).getText().trim() : "";
    }
}
