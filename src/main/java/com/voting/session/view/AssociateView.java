package com.voting.session.view;

import jakarta.validation.constraints.NotNull;

public class AssociateView {

    @NotNull(message = "name field is mandatory")
    private String name;

    @NotNull(message = "primaryDocumentNumber field is mandatory")
    private String primaryDocumentNumber;

    @NotNull(message = "birthDate field is mandatory")
    private String birthDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrimaryDocumentNumber() {
        return primaryDocumentNumber;
    }

    public void setPrimaryDocumentNumber(String primaryDocumentNumber) {
        this.primaryDocumentNumber = primaryDocumentNumber;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "AssociateView{" +
                "name='" + name + '\'' +
                ", primaryDocumentNumber='" + primaryDocumentNumber + '\'' +
                ", birthDate='" + birthDate + '\'' +
                '}';
    }
}
