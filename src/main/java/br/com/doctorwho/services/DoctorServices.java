package br.com.doctorwho.services;
import org.springframework.stereotype.Service;
import br.com.doctorwho.repositories.DoctorRepository;
@Service
public class DoctorServices {

    DoctorRepository doctorRepository;


    public DoctorServices(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }
}
