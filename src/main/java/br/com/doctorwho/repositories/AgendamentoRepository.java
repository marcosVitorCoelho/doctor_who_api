package br.com.doctorwho.repositories;

import br.com.doctorwho.models.AgendamentoModel;
import br.com.doctorwho.models.DoctorModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.UUID;

public interface AgendamentoRepository extends JpaRepository<AgendamentoModel, UUID> {
    Boolean existsByDatetimeAndDoctor(Date datetime, DoctorModel doctorModel);
    boolean existsByCode(long code);
    AgendamentoModel findByCode(long code);


}
