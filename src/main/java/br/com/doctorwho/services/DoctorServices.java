package br.com.doctorwho.services;
import br.com.doctorwho.models.DoctorModel;
import org.springframework.stereotype.Service;
import br.com.doctorwho.repositories.DoctorRepository;
@Service
public class DoctorServices {

   final DoctorRepository doctorRepository;


    public DoctorServices(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public DoctorModel save(DoctorModel doctorModel){
        return doctorRepository.save(doctorModel);
    }

    public boolean existsByCpf(String Cpf){
        return doctorRepository.existsByCpf(Cpf);
    }

    public boolean existsByRg(String Rg){
        return doctorRepository.existsByRg(Rg);
    }

    public boolean existsByPhoneNumber(String PhoneNumber){
        return doctorRepository.existsByPhoneNumber(PhoneNumber);
    }
}
