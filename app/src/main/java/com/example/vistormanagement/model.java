package com.example.vistormanagement;

public class model {
    String name,email,phone,randId,purpose,period;

    public model() {
    }

    public model(String name, String email, String phone, String randId, String purpose, String period) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.randId = randId;
        this.purpose = purpose;
        this.period = period;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRandId() {
        return randId;
    }

    public void setRandId(String randId) {
        this.randId = randId;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
