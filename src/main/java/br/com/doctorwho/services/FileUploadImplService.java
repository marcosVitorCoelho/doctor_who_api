package br.com.doctorwho.services;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;

import java.util.Map;
import java.util.UUID;

@Service
public class FileUploadImplService implements FileUpload{
   final Cloudinary cloudinary;

    public FileUploadImplService(Cloudinary cloudinary){
        this.cloudinary = cloudinary;
    }

    @Override
    public String uploadFile(MultipartFile multipartFile) throws IOException {
        return cloudinary.uploader()
                .upload(multipartFile.getBytes(),
                        Map.of("public_id", UUID.randomUUID().toString()))
                .get("url")
                .toString();
    } 
}
