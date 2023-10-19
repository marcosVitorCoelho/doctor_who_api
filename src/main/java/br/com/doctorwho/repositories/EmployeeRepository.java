package br.com.doctorwho.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.doctorwho.models.EmployeeModel;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeModel, UUID>{
    boolean existsByCpf(String cpf);
    boolean existsByRg(String rg);
    boolean existsByPhoneNumber(String phoneNumber);
    EmployeeModel findByFullName(String name);
}
