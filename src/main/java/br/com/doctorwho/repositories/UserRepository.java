package br.com.doctorwho.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.doctorwho.user.User;

public interface UserRepository extends JpaRepository<User, String> {
  UserDetails findByLogin(String login);
}
