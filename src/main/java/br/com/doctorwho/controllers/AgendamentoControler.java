package br.com.doctorwho.controllers;

import br.com.doctorwho.dto.AgendamentoDto;

import br.com.doctorwho.models.AgendamentoModel;

import br.com.doctorwho.models.DoctorModel;
import br.com.doctorwho.models.PacientModel;
import br.com.doctorwho.services.AgendamentoServices;
import br.com.doctorwho.services.DoctorServices;
import br.com.doctorwho.services.PacientService;
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
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/agendamento")
public class AgendamentoControler {

    final AgendamentoServices agendamentoServices;
    
    @Autowired
    private DoctorServices doctorServices;

    @Autowired
    private PacientService pacientService;

    public AgendamentoControler(AgendamentoServices agendamentoServices){
        this.agendamentoServices = agendamentoServices;
    }


    @PostMapping
    public ResponseEntity<Object> saveAgendamento(@RequestBody @Valid AgendamentoDto agendamentoDto) {

        PacientModel pacient = pacientService.findByFullName(agendamentoDto.getPacient().getFullName()) ;
        if (pacient == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pacient Not found") ;
        }
        DoctorModel doctor = doctorServices.findByFullName(agendamentoDto.getDoctor().getFullName()) ;
        if (doctor == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor Not found");
        }
         var agendamentoModel = new AgendamentoModel();
        BeanUtils.copyProperties(agendamentoDto, agendamentoModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(agendamentoServices.save(agendamentoModel));
    }


    @GetMapping
    public ResponseEntity<List<AgendamentoModel>> getAllAgendamentos() {
        return ResponseEntity.status(HttpStatus.OK).body(agendamentoServices.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneAgendamento(@PathVariable(value = "id") UUID id){
        Optional<AgendamentoModel> agendamentoModelOptional = agendamentoServices.findByid(id);
        if (!agendamentoModelOptional.isPresent()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
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
        agendamentoModel.setAppointmentDate(agendamentoDto.getAppointmentDate());
        agendamentoModel.setPacient(agendamentoDto.getPacient());
        agendamentoModel.setDoctor(agendamentoDto.getDoctor());
        agendamentoModel.setAppointmentType(agendamentoDto.getAppointmentType());
        agendamentoModel.setIsreturn(agendamentoDto.getIsreturn());

        return ResponseEntity.status(HttpStatus.OK).body(agendamentoServices.save(agendamentoModel) );
    }


}
