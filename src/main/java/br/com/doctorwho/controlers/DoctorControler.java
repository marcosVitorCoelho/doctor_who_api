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
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/doctor")
public class DoctorControler {
    final DoctorServices doctorServices;

    public DoctorControler(DoctorServices doctorServices) {this.doctorServices = doctorServices;}

    @PostMapping
    public ResponseEntity<Object> saveDoctor(@RequestBody@Valid DoctorDto doctorDto ){
        if (doctorServices.existsByRg(doctorDto.getRg())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLICT: the RG number has already been registered on the system");
        }

        if (doctorServices.existsByCpf(doctorDto.getCpf())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLICT: the CPF number has already been registered on the system");
        }
        if (doctorServices.existsByPhoneNumber(doctorDto.getPhoneNumber())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLICT: the phone number has already been registered on the system");
        }
        var doctorModel = new DoctorModel();
        BeanUtils.copyProperties(doctorDto, doctorModel);
        doctorModel.setRegistrationDate((LocalDateTime.now(ZoneId.of("UTC"))));
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorServices.save(doctorModel));
    }
}
