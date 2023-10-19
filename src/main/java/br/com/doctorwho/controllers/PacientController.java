package br.com.doctorwho.controllers;
import br.com.doctorwho.dto.PacientDto;
import br.com.doctorwho.models.PacientModel;
import br.com.doctorwho.services.PacientService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/pacient1")
public class PacientController {

    final PacientService pacientService;

    @Autowired
    private FileUploadController fileUploadController;


    public PacientController(PacientService pacientService) {
        this.pacientService = pacientService;
    }


    @PostMapping
    public ResponseEntity<Object> savePacient(@RequestPart("pacientDto") @Valid PacientDto pacientDto,
                                             @RequestPart("file") MultipartFile file) {
        /*verifica se os dados já existem, caso não exitam, ele cria novo médico*/
        if (pacientService.existsByRg(pacientDto.getRg())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLICT: the RG number has already been registered on the system.");
        }

        if (pacientService.existsByCpf(pacientDto.getCpf())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLICT: the CPF number has already been registered on the system.");
        }
        if (pacientService.existsByPhoneNumber(pacientDto.getPhoneNumber())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLICT: the phone number has already been registered on the system.");
        }
        if (pacientService.existsByEmail((pacientDto.getEmail()))){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLICT: the email has already been registered on the system");
        }

        var pacientModel = new PacientModel();
        BeanUtils.copyProperties(pacientDto, pacientModel);

        ResponseEntity<String> uploadResponse = fileUploadController.uploadFile(file);

        if (uploadResponse.getStatusCode() != HttpStatus.OK) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao fazer o upload da imagem.");
        }

        String imageURL = uploadResponse.getBody();
        pacientModel.setCloudinaryImageURL(imageURL);
        pacientModel.setRegistrationDate((LocalDateTime.now(ZoneId.of("UTC"))));
        return ResponseEntity.status(HttpStatus.CREATED).body(pacientService.save(pacientModel));
    }
    /* Pega todos medicos no banoc de dados*/
    @GetMapping
    public ResponseEntity<List<PacientModel>> getAllPacient(){
        return ResponseEntity.status(HttpStatus.OK).body(pacientService.findAll());
    }

    /*Pega um médico pelo id*/
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOnePacient(@PathVariable(value = "id") UUID id) {
        Optional<PacientModel> pacientModelOptional = pacientService.findById(id);
        if (!pacientModelOptional.isPresent()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pacient not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(pacientModelOptional.get());
    }

    /* deleta pelo id*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePacient(@PathVariable(value = "id")UUID id){
        Optional<PacientModel> pacientModelOptional = pacientService.findById(id);
        if (!pacientModelOptional.isPresent()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pacient not found");
        }
       pacientService.delete(pacientModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Pacient deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePacient(@PathVariable(value = "id")UUID id,
                                               @RequestBody @Valid PacientDto pacientDto){
        Optional<PacientModel> pacientModelOptional = pacientService.findById(id);
        if (!pacientModelOptional.isPresent()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pacientr not found" );
        }
        var pacientModel = pacientModelOptional.get();
        pacientModel.setRg(pacientDto.getRg());
        pacientModel.setCpf(pacientDto.getCpf());
        pacientModel.setEmail(pacientDto.getEmail());
        pacientModel.setPhoneNumber(pacientDto.getPhoneNumber());
        pacientModel.setAddress(pacientDto.getAddress());
        pacientModel.setFullName(pacientDto.getFullName());
        pacientModel.setBirthday(pacientDto.getBirthday());
        return ResponseEntity.status(HttpStatus.OK).body(pacientService.save(pacientModel) );
    }
}

