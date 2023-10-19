package br.com.doctorwho.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.doctorwho.dto.EmployeeDto;
import br.com.doctorwho.models.EmployeeModel;
import br.com.doctorwho.services.EmployeeService;
import jakarta.validation.Valid;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/employees")
public class EmployeeController {
    final EmployeeService employeeService;

    @Autowired
    private FileUploadController fileUploadController;

    public EmployeeController(EmployeeService employeeService) {this.employeeService = employeeService;}

    @PostMapping
    public ResponseEntity<Object> saveDoctor(@RequestPart("employeeDto") @Valid EmployeeDto employeeDto,
        @RequestPart("file") MultipartFile file) {


        if (employeeService.existsByRg(employeeDto.getRg())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLICT: the RG number has already been registered on the system.");
        }

        if (employeeService.existsByCpf(employeeDto.getCpf())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLICT: the CPF number has already been registered on the system.");
        }
        if (employeeService.existsByPhoneNumber(employeeDto.getPhoneNumber())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLICT: the phone number has already been registered on the system.");
        }

        var employeeModel = new EmployeeModel();
        BeanUtils.copyProperties(employeeDto, employeeModel);

        ResponseEntity<String> uploadResponse = fileUploadController.uploadFile(file);

        
        if (uploadResponse.getStatusCode() != HttpStatus.OK) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fail to upload image");
        }
        
        String imageURL = uploadResponse.getBody();
        employeeModel.setCloudinaryImageURL(imageURL);
        
        employeeModel.setRegistrationDate((LocalDateTime.now(ZoneId.of("UTC"))));
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.save(employeeModel));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeModel>> getAllDoctors(){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneDoctor(@PathVariable(value = "id")UUID id){
        Optional<EmployeeModel> employeeModelOptional = employeeService.findById(id);
    if (!employeeModelOptional.isPresent()){
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
    }
    return ResponseEntity.status(HttpStatus.OK).body(employeeModelOptional.get());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDoctor(@PathVariable(value = "id")UUID id){
        Optional<EmployeeModel> employeeModelOptional = employeeService.findById(id);
        if (!employeeModelOptional.isPresent()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        }
        employeeService.delete(employeeModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Employee deleted");
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateDoctor(@PathVariable(value = "id")UUID id,
    @RequestBody @Valid EmployeeDto employeeDto){
        Optional<EmployeeModel> employeeModelOptional = employeeService.findById(id);
        if (!employeeModelOptional.isPresent()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found" );
        }
        var employeeModel = employeeModelOptional.get();
        employeeModel.setRg(employeeDto.getRg());
        employeeModel.setCpf(employeeDto.getCpf());
        employeeModel.setEmail(employeeDto.getEmail());
        employeeModel.setPhoneNumber(employeeDto.getPhoneNumber());
        employeeModel.setAddress(employeeDto.getAddress());
        employeeModel.setFullName(employeeDto.getFullName());
        employeeModel.setBirthday(employeeDto.getBirthday());
        employeeModel.setHired(employeeDto.getHired());
        employeeModel.setFired(employeeDto.getFired());
        employeeModel.setRole(employeeDto.getRole());

        return ResponseEntity.status(HttpStatus.OK).body(employeeService.save(employeeModel));
    }
}
