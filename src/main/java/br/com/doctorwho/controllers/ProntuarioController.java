package br.com.doctorwho.controllers;


import br.com.doctorwho.models.ProntuarioModel;
import br.com.doctorwho.services.ProntuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/prontuarios")
public class ProntuarioController {

    @Autowired
    private ProntuarioService prontuarioService;

    @GetMapping
    public List<ProntuarioModel> getAllProntuarios() {
        return prontuarioService.getAllProntuarios();
    }

    @GetMapping("/{id}")
    public ProntuarioModel getProntuarioById(@PathVariable UUID id) {
        return prontuarioService.getProntuarioById(id);
    }

    @PostMapping
    public ProntuarioModel createProntuario(@RequestBody ProntuarioModel prontuario) {
        return prontuarioService.createProntuario(prontuario);
    }

    @PutMapping("/{id}")
    public ProntuarioModel updateProntuario(@PathVariable UUID id, @RequestBody ProntuarioModel updatedProntuario) {
        return prontuarioService.updateProntuario(id, updatedProntuario);
    }

    @DeleteMapping("/{id}")
    public void deleteProntuario(@PathVariable UUID id) {
        prontuarioService.deleteProntuario(id);
    }


}