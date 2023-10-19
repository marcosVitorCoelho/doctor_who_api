package br.com.doctorwho.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Table(name = "TB_PROCEDIMENTO")
@Entity

public class ProcedimentosModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    public String description;
    @Column(nullable = false)
    private float value;
    @Column(nullable = false)
    private String obs;
}
