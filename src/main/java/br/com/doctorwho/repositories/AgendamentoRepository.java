package br.com.doctorwho.repositories;

import br.com.doctorwho.models.AgendamentoModel;
import br.com.doctorwho.models.DoctorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.UUID;
@Repository
public interface AgendamentoRepository extends JpaRepository<AgendamentoModel, UUID> {
    Boolean existsByDatetimeAndDoctorname(String datetime, String doctorModel);
    boolean existsByCode(String code );



}
