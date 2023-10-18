package br.com.doctorwho.repositories;

import br.com.doctorwho.models.DoctorModel;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorModel, UUID>   {

    boolean existsByCpf(String cpf);
    boolean existsByRg(String rg);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByCrm(String crm);
    DoctorModel findByFullName(String name);

}
