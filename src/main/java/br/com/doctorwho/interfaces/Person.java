package br.com.doctorwho.interfaces;

import java.time.LocalDateTime;

import br.com.doctorwho.models.AddressModel;

public interface Person {
    String getFirstName();
    void setFirstName(String firstName);

    String getLastName();
    void setLastName(String lastName);

    String getRg();
    void setRg(String rg);

    String getCpf();
    void setCpf(String cpf);

    String getEmail();
    void setEmail(String email);

    String getPhoneNumber();
    void setPhoneNumber(String phoneNumber);

    String getBirthday();
    void setBirthday(String birthday);

    LocalDateTime getRegistrationDate();
    void setRegistrationDate(LocalDateTime registrationDate);

    AddressModel getAddress();
    void setAddress(AddressModel address);
}