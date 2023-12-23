package com.example.englishg.Modals;

public class AdminModal {
    String email,possword;

    public AdminModal() {
    }

    public AdminModal(String email, String possword) {
        this.email = email;
        this.possword = possword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPossword() {
        return possword;
    }

    public void setPossword(String possword) {
        this.possword = possword;
    }
}
