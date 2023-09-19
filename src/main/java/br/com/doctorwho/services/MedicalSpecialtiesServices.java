package br.com.doctorwho.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.doctorwho.models.MedicalSpecialtyModel;
import br.com.doctorwho.repositories.MedicalSpecialtiesRepository;
import jakarta.transaction.Transactional;

@Service
public class MedicalSpecialtiesServices {
    
    final MedicalSpecialtiesRepository medicalSpecialtiesRepository;

    public MedicalSpecialtiesServices(MedicalSpecialtiesRepository medicalSpecialtiesRepository) {
        this.medicalSpecialtiesRepository = medicalSpecialtiesRepository;
    }

    public List<MedicalSpecialtyModel> findAllSpeciality(){
        return medicalSpecialtiesRepository.findAll();
    }

    public Optional<MedicalSpecialtyModel> findById(UUID id){
        return medicalSpecialtiesRepository.findById(id);
        
    }

    public boolean existsByTitle(String title){
        return medicalSpecialtiesRepository.existsByTitle(title);
    }
    @Transactional
    public MedicalSpecialtyModel save(MedicalSpecialtyModel medicalSpecialtyModel){
        return medicalSpecialtiesRepository.save(medicalSpecialtyModel);
    }

    @Transactional
    public void delete(MedicalSpecialtyModel medicalSpecialtyModel){
        medicalSpecialtiesRepository.delete(medicalSpecialtyModel);
    }

}