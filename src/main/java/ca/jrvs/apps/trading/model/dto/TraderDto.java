package ca.jrvs.apps.trading.model.dto;

import java.util.Date;

public class TraderDto {

    private String first_name;
    private String last_name;
    private Date dob;
    private String country;
    private String email;

    public TraderDto() {
    }

    public TraderDto(String first_name, String last_name, Date dob, String country, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.dob = dob;
        this.country = country;
        this.email = email;
    }

    /*
     * Test of Fluent entities
     */
    public TraderDto firstName(String first_name) {
        this.first_name = first_name;
        return this;
    }

    public TraderDto lastName(String last_name) {
        this.last_name = last_name;
        return this;
    }

    public TraderDto dateOfBirth(Date dob) {
        this.dob = dob;
        return this;
    }

    public TraderDto country(String country) {
        this.country = country;
        return this;
    }

    public TraderDto email(String email) {
        this.email = email;
        return this;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
