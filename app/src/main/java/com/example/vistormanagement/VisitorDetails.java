package com.example.vistormanagement;

public class VisitorDetails {
        String name,email,phone,purpose,period,randId;

    public VisitorDetails() {
    }

    public VisitorDetails( String randId,String name, String email, String phone, String purpose, String period) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.purpose = purpose;
        this.period = period;
        this.randId = randId;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
