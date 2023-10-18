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
    DoctorServices doctorServices;
    PacientService pacientService;
/* Falta terminar, não tá compelto*/
    @PostMapping
    public ResponseEntity<Object> saveAgendamento(@RequestBody @Valid AgendamentoDto agendamentoDto) {

        if (agendamentoServices.existByDoctorAndDatetime(agendamentoDto.getDoctorname(), agendamentoDto.getAppointmentDate())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This time isn't available");
        }
        PacientModel pacient = pacientService.findByFullName(agendamentoDto.getPacientfULLName());
        if (pacient == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pacient Not found");
        }
        DoctorModel doctor = doctorServices.findByFullName(agendamentoDto.getDoctorname());
        if (doctor == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor Not found");
        }
         var agendamentoModel = new AgendamentoModel();
        BeanUtils.copyProperties(agendamentoDto, agendamentoModel);

        agendamentoModel.setPacientCode(pacient.getId().toString());
        agendamentoModel.setPacientname(pacient.getFullName());
        agendamentoModel.setPacietPhoneumber(pacient.getPhoneNumber());

        agendamentoModel.setDoctorCode(doctor.getId().toString());
        agendamentoModel.setDoctorSpeciality(doctor.getMedicalSpecialty());
        agendamentoModel.setDoctorname(doctor.getFullName());
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
        agendamentoModel.setPacientname(agendamentoDto.getPacientfULLName());
        agendamentoModel.setDoctorname(agendamentoDto.getDoctorname());
        agendamentoModel.setAppointmentType(agendamentoDto.getAppointmentType());
       agendamentoModel.setIsreturn(agendamentoDto.getIsreturn());

        return ResponseEntity.status(HttpStatus.OK).body(agendamentoServices.save(agendamentoModel) );
    }


}
