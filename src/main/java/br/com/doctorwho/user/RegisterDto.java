package br.com.doctorwho.user;

public record RegisterDto(String login, String password, UserRole role) {

}
