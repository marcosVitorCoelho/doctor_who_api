package br.com.doctorwho.models;

import lombok.Data;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Data
@Embeddable
public class AddressModel {

    @NotBlank(message = "Logradouro is required")
    private String logradouro;

    @NotBlank(message = "Cidade is required")
    private String cidade;

    @NotBlank(message = "Bairro is required")
    private String Bairro;

    @NotBlank(message = "Número is required")
    private String Numero;
}
