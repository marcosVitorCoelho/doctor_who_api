package br.com.doctorwho.models;

import lombok.Data;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Data
@Embeddable
public class AddressModel {

    @NotBlank(message = "street is required")
    private String street;

    @NotBlank(message = "city is required")
    private String city;

    @NotBlank(message = "district is required")
    private String district;

    @NotBlank(message = "number is required")
    private String number;
}
