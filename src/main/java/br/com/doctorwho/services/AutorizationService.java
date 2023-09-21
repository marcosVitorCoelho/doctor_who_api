package br.com.doctorwho.services;

import br.com.doctorwho.repositories.AdminRespository;
import br.com.doctorwho.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AutorizationService implements UserDetailsService {


    AdminRespository adminRespository;
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException  {
        return adminRespository.findByLogin(login);
    }
}
