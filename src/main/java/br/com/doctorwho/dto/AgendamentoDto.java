package br.com.doctorwho.dto;


import br.com.doctorwho.models.DoctorModel;

import br.com.doctorwho.models.PacientModel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
@Data
public class AgendamentoDto {
        @NotBlank
        private long code;
        @NotBlank
        @Size(max = 8)
        private Date datetime;
        @NotBlank
        private PacientModel pacient;
        @NotBlank
        private DoctorModel doctor;
        @NotBlank
        private String  appointmentType;
        @NotBlank
        private String isreturn;


    }
