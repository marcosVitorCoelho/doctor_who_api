package br.com.doctorwho.controllers;
import br.com.doctorwho.dto.DoctorDto;
import br.com.doctorwho.models.DoctorModel;
import br.com.doctorwho.models.MedicalSpecialtyModel;
import br.com.doctorwho.services.DoctorServices;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/doctor")
public class DoctorController   {
    final DoctorServices doctorServices;

    @Autowired
    private FileUploadController fileUploadController;

    @Autowired
    private MedicalSpecialtyController medicalSpecialtyController;


    public DoctorController(DoctorServices doctorServices) {this.doctorServices = doctorServices;}

    @PostMapping
    public ResponseEntity<Object> saveDoctor(@RequestPart("doctorDto") @Valid DoctorDto doctorDto,
        @RequestPart("file") MultipartFile file) {

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
        if (doctorServices.existsByCrm(doctorDto.getCrm())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLICT: the CRM number has already been registered on the system.");
        }


        var doctorModel = new DoctorModel();
        BeanUtils.copyProperties(doctorDto, doctorModel);

        ResponseEntity<String> uploadResponse = fileUploadController.uploadFile(file);

        
        if (uploadResponse.getStatusCode() != HttpStatus.OK) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao fazer o upload da imagem.");
        }
        
        String imageURL = uploadResponse.getBody();
        doctorModel.setCloudinaryImageURL(imageURL);
        
        ResponseEntity<Object> specialtyResponse = medicalSpecialtyController.getOneSpecialty(doctorDto.getMedicalSpecialty().getId());

        if(specialtyResponse.getBody() instanceof MedicalSpecialtyModel){
            MedicalSpecialtyModel specialtyModel = (MedicalSpecialtyModel) specialtyResponse.getBody();
            if(specialtyModel != null){
                String specialtyName = specialtyModel.getTitle();
                doctorModel.setMedicalSpecialty(specialtyName);
            }
           
        }
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
    public ResponseEntity<Object> deleteDoctor(@PathVariable(value = "id")UUID id){
        Optional<DoctorModel> doctorModelOptional = doctorServices.findById(id);
        if (!doctorModelOptional.isPresent()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor not found");
        }
        doctorServices.delete(doctorModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Doctor deleted");
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateDoctor(@PathVariable(value = "id")UUID id,
    @RequestBody @Valid DoctorDto doctorDto){
        Optional<DoctorModel> doctorModelOptional = doctorServices.findById(id);
        if (!doctorModelOptional.isPresent()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor not found" );
        }
        var doctorModel = doctorModelOptional.get();
        doctorModel.setRg(doctorDto.getRg());
        doctorModel.setCpf(doctorDto.getCpf());
        doctorModel.setEmail(doctorDto.getEmail());
        doctorModel.setPhoneNumber(doctorDto.getPhoneNumber());
        doctorModel.setAddress(doctorDto.getAddress());
        doctorModel.setFirstName(doctorDto.getFirstName());
        doctorModel.setLastName(doctorDto.getLastName());
        doctorModel.setBirthday(doctorDto.getBirthday());
        doctorModel.setCrm(doctorDto.getCrm());


        return ResponseEntity.status(HttpStatus.OK).body(doctorServices.save(doctorModel) );
    }
}
