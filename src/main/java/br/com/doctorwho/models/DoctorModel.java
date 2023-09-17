package br.com.doctorwho.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

import br.com.doctorwho.interfaces.Person;

@Entity
@Table(name = "TB_DOCTORs")
@Data
public class DoctorModel implements Person{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true, length = 15)
    private String firstName;

    @Column(nullable = false, unique = true, length = 15)
    private String lastName;

    @Column(nullable = false, unique = true, length = 10)
    private String rg;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false, unique = true, length = 18)
    private String phoneNumber;

    @Column(nullable = false, length = 15)
    private String birthday;
    
    @Column(nullable = false)
    private LocalDateTime RegistrationDate;

    @Column(nullable = false)
    private AddressModel address;

    @Column(nullable = false)
    private String crm;
}
