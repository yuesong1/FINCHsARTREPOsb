package com.ethfin.artrepo.controller;

import com.ethfin.artrepo.service.UploadService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/upload")
@AllArgsConstructor
public class UploadController {
    private final UploadService uploadService;
    @PostMapping
    public void upload(){

    }
}
