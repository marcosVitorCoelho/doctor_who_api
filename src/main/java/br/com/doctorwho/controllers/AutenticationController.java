package br.com.doctorwho.controllers;

import br.com.doctorwho.admin.TokenServices;
import br.com.doctorwho.admin.User;
import br.com.doctorwho.dto.LoginResponseDto;
import br.com.doctorwho.dto.UserDto;
import br.com.doctorwho.dto.UserResgisterDto;
import br.com.doctorwho.repositories.AdminRespository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AutenticationController  {

    @Autowired
    private AdminRespository adminRespository;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private TokenServices tokenServices;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UserDto userDto){
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(userDto.login(), userDto.password());
            var auth = this.authenticationManager.authenticate(usernamePassword).getAuthorities();
            var token = tokenServices.generateToken((User) auth);
            return ResponseEntity.ok(new LoginResponseDto(token));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.badRequest().body("erro de autenticacao!");
        }
        
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseEntity.BodyBuilder> resgister(@RequestBody @Valid UserResgisterDto userResgisterDto){
        if (this.adminRespository.findByLogin(userResgisterDto.login()) != null) return 
        ResponseEntity.badRequest().build();

        String ecryptedPassWord = new BCryptPasswordEncoder().encode(userResgisterDto.password());
        User newUser = new User(userResgisterDto.login(), ecryptedPassWord, userResgisterDto.role());

        this.adminRespository.save(newUser);
        return ResponseEntity.ok().build();
    }

}

