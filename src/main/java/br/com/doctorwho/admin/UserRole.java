package br.com.doctorwho.admin;

public enum UserRole {
    ADMIN("ADMIN");
    private String role;

    UserRole(String role){
        this.role = role;
    }
    public String getRole() {
        return role;
    }
}

