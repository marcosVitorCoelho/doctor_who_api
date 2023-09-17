package br.com.doctorwho.repositories;
import br.com.doctorwho.models.MedicalSpecialtyModel;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalSpecialtiesRepository extends JpaRepository<MedicalSpecialtyModel, UUID>{
    boolean existsByTitle(String title);
}