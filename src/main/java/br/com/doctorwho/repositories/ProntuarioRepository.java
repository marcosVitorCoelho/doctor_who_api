package br.com.doctorwho.repositories;

import br.com.doctorwho.models.ProntuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ProntuarioRepository extends JpaRepository<ProntuarioModel, UUID> {
}