package br.com.doctorwho.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.bind.annotation.ResponseBody;

@Data
public class DoctorDto {
    @NotBlank
    @Size(min = 10, max = 10)
    private String rg;
    @NotBlank
    @Size(min = 11, max = 11)
    private String cpf;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String phoneNumber;


}
