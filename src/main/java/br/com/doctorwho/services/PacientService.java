package br.com.doctorwho.services;

import br.com.doctorwho.models.PacientModel;
import br.com.doctorwho.repositories.PacientRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PacientService  {
    final PacientRepository pacientRepository;

    public PacientService(PacientRepository pacientRepository) {
        this.pacientRepository = pacientRepository;
    }

    public PacientModel save(PacientModel pacientModel){
        return pacientRepository.save(pacientModel);
    }

    public boolean existsByCpf(String Cpf){
        return pacientRepository.existsByCpf(Cpf);
    }

    public boolean existsByRg(String Rg){
        return pacientRepository.existsByRg(Rg);
    }

    public boolean existsByPhoneNumber(String PhoneNumber){
        return pacientRepository.existsByPhoneNumber(PhoneNumber);
    }
    public boolean existsByEmail(String email) {
    return pacientRepository.existsByEmail(email);
    }


    public List<PacientModel> findAll(){
        return pacientRepository.findAll();
    }

    public Optional<PacientModel> findById(UUID id){
        return pacientRepository.findById(id);
    }
    @Transactional
    public void delete(PacientModel pacientModel){
        pacientRepository.delete(pacientModel);
    }
}


