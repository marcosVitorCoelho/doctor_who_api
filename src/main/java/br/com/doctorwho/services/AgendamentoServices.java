package br.com.doctorwho.services;

import br.com.doctorwho.models.AgendamentoModel;
import br.com.doctorwho.models.PacientModel;
import br.com.doctorwho.repositories.AgendamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AgendamentoServices {


    final  AgendamentoRepository agendamentoRepository;

    public AgendamentoServices(AgendamentoRepository agendamentoRepository)  {
        this.agendamentoRepository = agendamentoRepository;
    }

    public boolean existsByPacient(PacientModel pacient){
        return agendamentoRepository.existsByPacient(pacient);
    }

    public AgendamentoModel save( AgendamentoModel agendamentoModel) {
        return agendamentoRepository.save(agendamentoModel);}

    public List<AgendamentoModel> findAll(){
        return agendamentoRepository.findAll();
    }
    public Optional<AgendamentoModel> findByid(UUID id){
        return agendamentoRepository.findById(id);
    }
    public void delete(AgendamentoModel agendamentoModel){
        agendamentoRepository.delete(agendamentoModel);
    }

}

