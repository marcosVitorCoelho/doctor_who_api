package br.com.doctorwho.dto;

import br.com.doctorwho.admin.UserRole;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public record UserDto(String login, String password) {

}



