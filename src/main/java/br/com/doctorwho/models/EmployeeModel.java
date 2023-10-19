package br.com.doctorwho.models;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "TB_EMPLOYEES")
@Data
public class EmployeeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true, length = 15)
    private String fullName;

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

    @Column(nullable = false, length = 15)
    private String hired ;

    @Column(nullable = true, length = 15)
    private String fired;
    
    @Column(nullable = false)
    private LocalDateTime registrationDate;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private AddressModel address;

    private String cloudinaryImageURL;
}
