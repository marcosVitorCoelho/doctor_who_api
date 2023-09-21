package br.com.doctorwho.repositories;

import br.com.doctorwho.models.PacientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface PacientRepository extends JpaRepository<PacientModel, UUID>  {
    boolean existsByCpf(String cpf);
    boolean existsByRg(String rg);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByEmail(String email);
    UserDetails findByEmail(String email);
}
