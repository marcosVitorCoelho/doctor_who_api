package br.com.doctorwho.repositories;

import br.com.doctorwho.admin.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRespository extends JpaRepository<User, String>  {
    UserDetails findByLogin(String login);
}
