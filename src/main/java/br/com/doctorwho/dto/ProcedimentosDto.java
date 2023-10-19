package br.com.doctorwho.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProcedimentosDto {
    @NotBlank
    public String Description;
    @NotNull
    public  float value;
    @NotBlank
    public String obs;
}
