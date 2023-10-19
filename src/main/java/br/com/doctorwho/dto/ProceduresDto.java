package br.com.doctorwho.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProceduresDto {
    @NotBlank
    private String description;

    @NotBlank
    private String value;

    @NotBlank
    private String comments;
}
