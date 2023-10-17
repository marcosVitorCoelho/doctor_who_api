package br.com.doctorwho.models;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;


@Data
@Entity
@Table(name = "TB_AGENDAMENTO")
public class AgendamentoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private UUID id;

    private long code;

    private Date datetime;
    @ManyToOne
    private PacientModel pacient;
    @ManyToOne
    private DoctorModel doctor;

    private String  appointmentType;

    private String isreturn;

    /* Falta terminar, não tá compelto*/
}
