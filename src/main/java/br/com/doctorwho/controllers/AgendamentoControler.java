package br.com.doctorwho.controllers;

import br.com.doctorwho.dto.AgendamentoDto;

import br.com.doctorwho.models.AgendamentoModel;

import br.com.doctorwho.models.PacientModel;
import br.com.doctorwho.services.AgendamentoServices;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/agendamento")
public class AgendamentoControler {
    AgendamentoServices agendamentoServices;
/* Falta terminar, não tá compelto*/
    @PostMapping

    public ResponseEntity<Object> saveAgendamento(@RequestBody @Valid AgendamentoDto agendamentoDto) {
        /* Falta terminar, não tá compelto*/
        if (agendamentoServices.existByDoctorAndDatetime(agendamentoDto.getDoctor(), agendamentoDto.getDatetime())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This time isn't available");
        }


        if (agendamentoServices.exsitByCode(agendamentoDto.getCode())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("The code already exists");
        }
        AgendamentoModel agendamentoModel = new AgendamentoModel();
        BeanUtils.copyProperties(agendamentoDto, agendamentoModel);

        PacientModel pacienteModel = new PacientModel();
        pacienteModel.setFirstName(agendamentoDto.getPacient().getFirstName());
        pacienteModel.setFirstName(agendamentoDto.getPacient().getLastName());
        pacienteModel.setPhoneNumber(agendamentoDto.getDoctor().getPhoneNumber());

        agendamentoModel.setPacient(pacienteModel);

        AgendamentoModel savedAgendamento = agendamentoServices.save(agendamentoModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedAgendamento);
    }



    @GetMapping
    public ResponseEntity<List<AgendamentoModel>> getAllAgendamentos(){
        return ResponseEntity.status(HttpStatus.OK).body(agendamentoServices.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneAgendamento(@PathVariable(value = "id") UUID id){
        Optional<AgendamentoModel> agendamentoModelOptional = agendamentoServices.findByid(id);
        if (!agendamentoModelOptional.isPresent()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(" not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(agendamentoModelOptional.get());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAgendamento(@PathVariable(value = "id")UUID id){
        Optional<AgendamentoModel> agendamentoModelOptional = agendamentoServices.findByid(id);
        if (!agendamentoModelOptional.isPresent()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor not found");
        }
       agendamentoServices.delete(agendamentoModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAgendamento(@PathVariable(value = "id")UUID id,
                                               @RequestBody @Valid AgendamentoDto agendamentoDto){
        Optional<AgendamentoModel> agendamentoModeloptional = agendamentoServices.findByid(id);
        if (!agendamentoModeloptional.isPresent()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(" not found" );
        }
        var  agendamentoModel = agendamentoModeloptional.get();
        agendamentoModel.setCode(agendamentoDto.getCode());
        agendamentoModel.setDatetime(agendamentoDto.getDatetime());
        agendamentoModel.setPacient(agendamentoDto.getPacient());
        agendamentoModel.setDoctor(agendamentoDto.getDoctor());
        agendamentoModel.setAppointmentType(agendamentoDto.getAppointmentType());
        agendamentoModel.setIsreturn(agendamentoDto.getIsreturn());



        return ResponseEntity.status(HttpStatus.OK).body(agendamentoServices.save(agendamentoModel) );
    }


}
