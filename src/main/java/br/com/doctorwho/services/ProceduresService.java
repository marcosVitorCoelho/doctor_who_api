package br.com.doctorwho.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.doctorwho.models.ProceduresModel;
import br.com.doctorwho.repositories.ProceduresRepository;
import jakarta.transaction.Transactional;

@Service
public class ProceduresService {
    final ProceduresRepository proceduresRepository;

    public ProceduresService(ProceduresRepository proceduresRepository){
        this.proceduresRepository = proceduresRepository;
    }

    public boolean existsByDescription(String description){
        return proceduresRepository.existsByDescription(description);
    }

    public ProceduresModel save(ProceduresModel proceduresModel){
        return proceduresRepository.save(proceduresModel);
    }

    public List<ProceduresModel> findAll(){
        return proceduresRepository.findAll();
    }

     public Optional<ProceduresModel> findById(UUID id){
        return proceduresRepository.findById(id);
    }

    @Transactional
    public void delete(ProceduresModel proceduresModel){
        proceduresRepository.delete(proceduresModel);
    }
}
