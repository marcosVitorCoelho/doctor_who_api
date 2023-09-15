package br.com.doctorwho.models;

import lombok.Data;

@Data
public class Address {
    private String logradouro;
    private String cidade;
    private String estado;
    private String cep;

}
