package br.com.doctorwho.models;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "TB_SPECIALTIES")
@Data
public class MedicalSpecialtyModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true, length = 59)
    private String title;

    @Column(nullable = false, unique = true, length = 100)
    private String description;
}