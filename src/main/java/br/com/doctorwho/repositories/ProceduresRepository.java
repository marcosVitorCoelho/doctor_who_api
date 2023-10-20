package br.com.doctorwho.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.doctorwho.models.ProceduresModel;

@Repository
public interface ProceduresRepository extends JpaRepository<ProceduresModel, UUID>{
    boolean existsByDescription(String description);
}
