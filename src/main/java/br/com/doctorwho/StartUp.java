package br.com.doctorwho;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.cloudinary.Cloudinary;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
@RestController
public class StartUp {

    Dotenv dotenv = Dotenv.load();

    private final String CLOUD_NAME = dotenv.get("CLOUDINARY_NAME");
    private final String API_KEY = dotenv.get("CLOUDINARY_API_KEY");
    private final String API_SECRET = dotenv.get("CLOUDINARY_API_SECRET");

    public static void main(String[] args) {
        SpringApplication.run(StartUp.class, args);
    }

    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", CLOUD_NAME);
        config.put("api_key", API_KEY);
        config.put("api_secret", API_SECRET);
        return new Cloudinary(config);
    }

}
