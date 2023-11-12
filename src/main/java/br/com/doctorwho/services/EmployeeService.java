package br.com.doctorwho.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.doctorwho.models.EmployeeModel;
import br.com.doctorwho.repositories.EmployeeRepository;
import jakarta.transaction.Transactional;

@Service
public class EmployeeService {

    final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public EmployeeModel save(EmployeeModel employeeModel){
        return employeeRepository.save(employeeModel);
    }


    public List<EmployeeModel> findAll(){
        return employeeRepository.findAll();
    }

    public Optional<EmployeeModel> findById(UUID id){
        return employeeRepository.findById(id);
    }

    public boolean existsByCpf(String Cpf){
        return employeeRepository.existsByCpf(Cpf);
     }

    public boolean existsByRg(String Rg){
        return employeeRepository.existsByRg(Rg);
    }

    public boolean existsByPhoneNumber(String PhoneNumber){
        return employeeRepository.existsByPhoneNumber(PhoneNumber);
    }

    public EmployeeModel findByFullName(String name) {
        return employeeRepository.findByFullName(name);
    }

    @Transactional
    public void delete(EmployeeModel employeeModel){
        employeeRepository.delete(employeeModel);
    }
}
