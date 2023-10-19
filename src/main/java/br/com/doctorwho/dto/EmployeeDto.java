package br.com.doctorwho.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

import br.com.doctorwho.models.AddressModel;

@Data
public class EmployeeDto   {

    @NotBlank
    @Size(min = 1, max = 15)
    private String fullName;

    @NotBlank
    @Size(min = 10, max = 10)
    private String rg;

    @NotBlank
    @Size(min = 11, max = 11)
    private String cpf;

    @NotBlank
    @Email(message = "email format not allowed")
    private String email;

    @NotBlank
    private String phoneNumber;

    @Valid
    private AddressModel address;

    @NotBlank
    private String birthday;

    @NotBlank
    private String hired;

    private String fired;

    @NotBlank
    private String role;
}