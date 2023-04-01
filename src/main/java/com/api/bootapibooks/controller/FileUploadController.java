package com.api.bootapibooks.controller;

import com.api.bootapibooks.helper.FileUploadHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
public class FileUploadController {
    @PostMapping("/uploadFile")
    public ResponseEntity<String> uploadFile(@RequestBody MultipartFile file) {
        if (file.isEmpty() && file.getSize() >= 5000000l) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please enter file in proper size");
        }

        if (FileUploadHelper.uploadFile(file)) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(ServletUriComponentsBuilder.fromCurrentContextPath().
                    path("/images/").path(file.getOriginalFilename()).toUriString());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Sorry something went wrong!");
    }
}
