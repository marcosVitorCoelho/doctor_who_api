package br.com.doctorwho.repositories;

import br.com.doctorwho.admin.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface AdminRespository extends JpaRepository<User, UUID>  {
    UserDetails findByLogin(String login);
}
