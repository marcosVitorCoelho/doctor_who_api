package br.com.doctorwho.repositories;

import br.com.doctorwho.models.ProcedimentosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProcedimentoRespository  extends JpaRepository<ProcedimentosModel, UUID> {

     boolean existsByDescription(String description);
}
