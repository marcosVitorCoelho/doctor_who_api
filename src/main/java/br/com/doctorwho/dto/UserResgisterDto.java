package br.com.doctorwho.dto;

import br.com.doctorwho.admin.UserRole;
import lombok.Data;

@Data
public record UserResgisterDto(String login, String password, UserRole role) {
}
