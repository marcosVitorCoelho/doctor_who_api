package br.com.doctorwho.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.doctorwho.services.FileUploadImplService;

import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class FileUploadController {

    private final FileUploadImplService fileUploadImplService;

    public FileUploadController(FileUploadImplService fileUploadImplService) {
        this.fileUploadImplService = fileUploadImplService;
    }

    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestParam("image") MultipartFile multipartFile) {
        try {
            if (multipartFile.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O arquivo está vazio.");
            }

            String imageURL = fileUploadImplService.uploadFile(multipartFile);
            return ResponseEntity.status(HttpStatus.OK).body(imageURL);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao processar o arquivo.");
        }
    }
}