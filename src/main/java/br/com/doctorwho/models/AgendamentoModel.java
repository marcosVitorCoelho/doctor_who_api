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
    @GeneratedValue(strategy = GenerationType.AUTO  )
    private UUID id;


    private String code;
    private String datetime;
    @Column(nullable = false)
    private String pacientname;
    @Column(nullable = false)
    private String pacientCode = null;
    @Column(nullable = false)
    private String pacietPhoneumber;
    @Column(nullable = false)
    private String doctorname;
    @Column(nullable = false)
    private String doctorCode;
    @Column(nullable = false)
    private String doctorSpeciality = null;

    private String  appointmentType;

    private String isreturn;


}
