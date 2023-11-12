package br.com.doctorwho.services;

import br.com.doctorwho.models.AgendamentoModel;
import br.com.doctorwho.models.ProntuarioModel;
import br.com.doctorwho.repositories.ProntuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ProntuarioService {

    @Autowired
    private ProntuarioRepository prontuarioRepository;

    public List<ProntuarioModel> getAllProntuarios() {
        return prontuarioRepository.findAll();
    }

    public ProntuarioModel getProntuarioById(UUID id) {
        return prontuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prontuário não encontrado com o ID: " + id));
    }

    public ProntuarioModel createProntuario(ProntuarioModel prontuario) {
        prontuario.setDataCadastro(new Date());
        return prontuarioRepository.save(prontuario);
    }

    public ProntuarioModel updateProntuario(UUID id, ProntuarioModel updatedProntuario) {
        ProntuarioModel prontuario = prontuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prontuário não encontrado com o ID: " + id));

        prontuario.setPacient(updatedProntuario.getPacient());


        return prontuarioRepository.save(prontuario);
    }
    public void deleteProntuario(UUID id) {
        prontuarioRepository.deleteById(id);
    }


}