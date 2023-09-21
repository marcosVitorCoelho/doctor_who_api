package br.com.doctorwho.models;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;

import javax.naming.Name;
import java.util.UUID;


@Data
@Embeddable
public class AddressModel  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotBlank(message = "street is required")
    private String street;

    @NotBlank(message = "city is required")
    private String city;

    @NotBlank(message = "district is required")
    private String district;

    @NotBlank(message = "number is required")
    private String number;
}
