package br.com.doctorwho.dto;


import br.com.doctorwho.models.DoctorModel;

import br.com.doctorwho.models.PacientModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AgendamentoDto  {

        @NotBlank
        private String appointmentDate;

        @Valid
        private PacientModel pacient;

        @Valid
        private DoctorModel doctor;

        @NotBlank
        private String appointmentType;

        @NotNull
        private String isreturn;
    }
