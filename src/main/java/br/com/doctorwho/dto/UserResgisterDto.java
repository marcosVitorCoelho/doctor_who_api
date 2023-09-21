package br.com.doctorwho.dto;

import br.com.doctorwho.admin.UserRole;

public record UserResgisterDto(String login, String password, UserRole role)  {
}
