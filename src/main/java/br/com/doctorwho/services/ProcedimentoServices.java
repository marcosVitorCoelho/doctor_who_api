package br.com.doctorwho.services;


import br.com.doctorwho.models.ProcedimentosModel;

import br.com.doctorwho.repositories.ProcedimentoRespository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class ProcedimentoServices  {
   final ProcedimentoRespository procedimentoRespository;

    public ProcedimentoServices(ProcedimentoRespository procedimentoRespository) {
        this.procedimentoRespository = procedimentoRespository;
    }

    public ProcedimentosModel save(ProcedimentosModel procedimentosModel){
        return procedimentoRespository.save(procedimentosModel);
    }
    public List<ProcedimentosModel>  findAll(){
        return procedimentoRespository.findAll();
    }
    public Optional<ProcedimentosModel> findById(UUID id){
        return procedimentoRespository.findById(id);
     }
    @Transactional
    public void delete(ProcedimentosModel procedimentosModel){
        procedimentoRespository.delete(procedimentosModel);
    }

    public Boolean existByDescription(String description){
        return procedimentoRespository.existsByDescription(description);
    }





}
