package br.com.doctorwho.dto;


import br.com.doctorwho.models.DoctorModel;

import br.com.doctorwho.models.PacientModel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AgendamentoDto  {
        @NotBlank
        @Size(max = 8)
        private String appointmentDate;
        @NotBlank
        private String pacientfULLName;
        @NotBlank
        private String doctorname;
        @NotBlank
        private String  appointmentType;
        @NotBlank
        private String isreturn;


    }
