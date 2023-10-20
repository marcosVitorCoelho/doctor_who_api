package br.com.doctorwho.controllers;


import br.com.doctorwho.dto.ProcedimentosDto;
;
import br.com.doctorwho.models.ProcedimentosModel;

import br.com.doctorwho.services.ProcedimentoServices;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;
import java.util.Optional;
import java.util.UUID;
@RestController
@RequestMapping("/procedimentos")
public class ProcedimentosController {
    final
    ProcedimentoServices procedimentoServices;

    public ProcedimentosController(ProcedimentoServices procedimentoServices) {
        this.procedimentoServices = procedimentoServices;
    }

    @PostMapping
    public ResponseEntity<Object> saveProcedimentos(@RequestBody @Valid ProcedimentosDto procedimentosDto ) {

        if (procedimentoServices.existByDescription(procedimentosDto.getDescription())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This procedure already exist.");
        }
        var procedimentosModel = new ProcedimentosModel();
        BeanUtils.copyProperties(procedimentosDto, procedimentosModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(procedimentoServices.save(procedimentosModel));

    }

    @GetMapping
    public ResponseEntity<List<ProcedimentosModel>> getAllprocedimentos(){
        return ResponseEntity.status(HttpStatus.OK).body(procedimentoServices.findAll());
    }


    @GetMapping("/{id}" )
    public ResponseEntity<Object> getOneProcedimento(@PathVariable(value = "id") UUID id){
        Optional<ProcedimentosModel> procedimentosModelOptional = procedimentoServices.findById(id);
        if (!procedimentosModelOptional.isPresent()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Procediment not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(procedimentosModelOptional.get());
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProcedimento(@PathVariable(value = "id")UUID id){
        Optional<ProcedimentosModel> procedimentosModelOptional = procedimentoServices.findById(id);
        if (!procedimentosModelOptional.isPresent()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor not found");
        }
        procedimentoServices.delete(procedimentosModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("procedimento deleted");
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProcedimento(@PathVariable(value = "id")UUID id,
                                               @RequestBody @Valid ProcedimentosDto procedimentosDto){
        Optional<ProcedimentosModel> procedimentosModelOptional = procedimentoServices.findById(id);
        if (!procedimentosModelOptional.isPresent()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("dont found" );
        }
        var procedimentoModel = procedimentosModelOptional.get();
        procedimentoModel.setDescription(procedimentosDto.getDescription());
        procedimentoModel.setValue(procedimentosDto.getValue());
        procedimentoModel.setDescription(procedimentosDto.getDescription());


        return ResponseEntity.status(HttpStatus.OK).body(procedimentoServices.save(procedimentoModel) );
    }
}
