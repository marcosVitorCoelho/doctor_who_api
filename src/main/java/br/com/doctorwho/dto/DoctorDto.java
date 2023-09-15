package br.com.doctorwho.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;
import jakarta.validation.constraints.NotBlank;
@Data
public class DoctorDto {
    @NotBlank
    private String name;
    @NotBlank
    private Date birthDate;
    @NotBlank
    private String crm;
}
