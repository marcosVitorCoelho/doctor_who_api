package br.com.doctorwho.models;
import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;


@Data
@Entity
@Table(name = "TB_AGENDAMENTO")
public class AgendamentoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO  )
    private UUID id;

    @Column(nullable = false, length = 15)
    private String appointmentDate;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private PacientModel pacient;

    @ManyToOne
    @JoinColumn(name = "doutor_id")
    private DoctorModel doctor;

    @Column(nullable = false)
    private String appointmentType;

    @Column(name = "isreturn")
    private String isreturn;
}
