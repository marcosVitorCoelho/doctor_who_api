package br.com.doctorwho.controllers;
import br.com.doctorwho.services.MedicalSpecialtiesServices;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.doctorwho.dto.MedicalSpecialtyDto;
import br.com.doctorwho.models.MedicalSpecialtyModel;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/specialties")
public class MedicalSpecialtyController {
    
    final MedicalSpecialtiesServices medicalSpecialtiesServices;

    public MedicalSpecialtyController(MedicalSpecialtiesServices medicalSpecialtiesServices) {
    this.medicalSpecialtiesServices = medicalSpecialtiesServices;
    }

    @PostMapping
    public ResponseEntity<Object> saveSpecialty(@RequestBody @Valid MedicalSpecialtyDto medicalSpecialtyDto){
        if(medicalSpecialtiesServices.existsByTitle(medicalSpecialtyDto.getTitle())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLICT: This specialty has already been registered on the system.");
        }

        var medicalSpecialtyModel = new MedicalSpecialtyModel();
        BeanUtils.copyProperties(medicalSpecialtyDto, medicalSpecialtyModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicalSpecialtiesServices.save(medicalSpecialtyModel));
    }

    @GetMapping
    public ResponseEntity<List<MedicalSpecialtyModel>> getAllSpeciality(){
        return ResponseEntity.status(HttpStatus.OK).body(medicalSpecialtiesServices.findAllSpeciality());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneSpecialty(@PathVariable(value = "id")UUID id){
        Optional<MedicalSpecialtyModel> specialtyModelOptional = medicalSpecialtiesServices.findById(id);
        if(!specialtyModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Specialty not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(specialtyModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOneSpecialty(@PathVariable(value = "id")UUID id){
        Optional<MedicalSpecialtyModel> specialtyModelOptional = medicalSpecialtiesServices.findById(id);
        if(!specialtyModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Specialty not found");
        }
        medicalSpecialtiesServices.delete(specialtyModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Specialty deleted");
    }
}
