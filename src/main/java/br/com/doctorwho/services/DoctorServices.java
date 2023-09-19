package br.com.doctorwho.services;
import br.com.doctorwho.models.DoctorModel;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import br.com.doctorwho.repositories.DoctorRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DoctorServices  {

    final DoctorRepository doctorRepository;


    public DoctorServices(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
     }

    public DoctorModel save(DoctorModel doctorModel){
        return doctorRepository.save(doctorModel);
    }

    public boolean existsByCpf(String Cpf){
        return doctorRepository.existsByCpf(Cpf);
    }

    public boolean existsByRg(String Rg){
        return doctorRepository.existsByRg(Rg);
    }

    public boolean existsByPhoneNumber(String PhoneNumber){
        return doctorRepository.existsByPhoneNumber(PhoneNumber);
    }

    public boolean existsByCrm(String crm){
        return doctorRepository.existsByCrm(crm);
    }

    public List<DoctorModel> findAll(){
        return doctorRepository.findAll();
    }

    public Optional<DoctorModel> findById(UUID id){
        return doctorRepository.findById(id);
    }
@Transactional
    public void delete(DoctorModel doctorModel){
        doctorRepository.delete(doctorModel);
    }
}
