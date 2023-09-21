package br.com.doctorwho.admin;

public enum UserRole {
    USER("ADMIN");
    private String role;

    UserRole(String role){
        this.role = role;
    }
    public String getRole() {
        return role;
    }
}

