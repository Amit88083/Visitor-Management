package com.example.vistormanagement;

public class InformationClass {
    String randId,name,email;

    public InformationClass() {
    }

    public InformationClass(String randId, String name, String email) {
        this.randId = randId;
        this.name = name;
        this.email = email;
    }

    public String getRandId() {
        return randId;
    }

    public void setRandId(String randId) {
        this.randId = randId;
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
}
