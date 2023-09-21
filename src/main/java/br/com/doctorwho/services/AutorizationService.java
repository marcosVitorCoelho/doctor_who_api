package br.com.doctorwho.services;

import br.com.doctorwho.repositories.AdminRespository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AutorizationService implements UserDetailsService {
    final AdminRespository adminRespository;

    public AutorizationService(AdminRespository adminRespository) {
        this.adminRespository = adminRespository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException   {
        return adminRespository.findByLogin(login);
    }
}
