package br.com.doctorwho.repositories;

import br.com.doctorwho.models.DoctorModel;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<DoctorModel, UUID> {
}
