package com.yourapp.demo.controller;

import com.yourapp.demo.Dto.AnalyzeResponse;
import com.yourapp.demo.service.SecondSortingService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/audits/second-sorting")
public class SecondSortingController {

    private final SecondSortingService service;

    public SecondSortingController(SecondSortingService service) {
        this.service = service;
    }

    @PostMapping(value = "/analyze", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AnalyzeResponse analyze(
            @RequestPart("exceptionFile") MultipartFile exceptionFile,
            @RequestPart("waveFile") MultipartFile waveFile
    ) {
        return service.analyze(waveFile, exceptionFile);
    }
}