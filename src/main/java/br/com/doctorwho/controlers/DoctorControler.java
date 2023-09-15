package br.com.doctorwho.controlers;
import br.com.doctorwho.dto.DoctorDto;
import br.com.doctorwho.models.DoctorModel;
import br.com.doctorwho.services.DoctorServices;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/doctor")
public class DoctorControler {
    final DoctorServices doctorServices;

    public DoctorControler(DoctorServices doctorServices) {this.doctorServices = doctorServices;}

    @PostMapping
    public ResponseEntity<Object> saveDoctor(@RequestBody@Valid DoctorDto doctorDto ) {
       /*verifica se os dados já existem, caso não exitam, ele cria novo médico*/
        if (doctorServices.existsByRg(doctorDto.getRg())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLICT: the RG number has already been registered on the system.");
        }

        if (doctorServices.existsByCpf(doctorDto.getCpf())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLICT: the CPF number has already been registered on the system.");
        }
        if (doctorServices.existsByPhoneNumber(doctorDto.getPhoneNumber())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLICT: the phone number has already been registered on the system.");
        }
        var doctorModel = new DoctorModel();
        BeanUtils.copyProperties(doctorDto, doctorModel);
        doctorModel.setRegistrationDate((LocalDateTime.now(ZoneId.of("UTC"))));
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorServices.save(doctorModel));
    }
/* Pega todos medicos no banoc de dados*/
    @GetMapping
    public ResponseEntity<List<DoctorModel>> getAllDoctors(){
        return ResponseEntity.status(HttpStatus.OK).body(doctorServices.findAll());
    }

    /*Pega um médico pelo id*/
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneDoctor(@PathVariable(value = "id")UUID id){
        Optional<DoctorModel> doctorModelOptional = doctorServices.findById(id);
    if (!doctorModelOptional.isPresent()){
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor not found");
    }
    return ResponseEntity.status(HttpStatus.OK).body(doctorModelOptional.get());
    }

    /* deleta pelo id*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletDoctor(@PathVariable(value = "id")UUID id){
        Optional<DoctorModel> doctorModelOptional = doctorServices.findById(id);
        if (!doctorModelOptional.isPresent()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor not found");
        }
        doctorServices.delete(doctorModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Doctor deleted");
    }
}
