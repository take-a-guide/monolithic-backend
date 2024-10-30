package br.com.takeaguide.userservice.domain.entities;

import java.time.LocalDateTime;


public class User {
  
    private String cpf;
    private String name;
    private String email;
    private String password;
    private String phone;
    private int userTypeId;
    private LocalDateTime deletedAt;
    


    public User() {}


    public User(String cpf, String name, String email, String password, String phone, int userTypeId, LocalDateTime deletedAt) {
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.userTypeId = userTypeId;
        this.deletedAt = deletedAt;
    }


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(int userTypeId) {
        this.userTypeId = userTypeId;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
}
