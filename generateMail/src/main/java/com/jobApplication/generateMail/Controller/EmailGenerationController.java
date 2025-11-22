package com.jobApplication.generateMail.Controller;

import com.jobApplication.generateMail.Model.Recruiter;
import com.jobApplication.generateMail.Service.EmailGenerationService;
import com.jobApplication.generateMail.Service.RecruiterPdfExtractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/email")
public class EmailGenerationController {

    @Autowired
    private EmailGenerationService emailGenerationService;

    @Autowired
    private RecruiterPdfExtractorService recruiterPdfExtractorService;

    @PostMapping
    public ResponseEntity<Boolean> generateEmail() {
        emailGenerationService.generateEmail();

        return ResponseEntity.ok(true);
    }

    @GetMapping
    public ResponseEntity<List<Recruiter>> getRecruiters() {
        return ResponseEntity.ok(recruiterPdfExtractorService.extractRecruiterFromPdf());
    }
}
