package br.com.doctorwho.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TB_PRONTUARIO" )
public class ProntuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;


    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private PacientModel pacient;

}
