package ca.jrvs.apps.trading.model.domain;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "trader")
public class Trader {

    @OneToMany
    @JoinColumn(name = "trader_id", updatable = false)
    @OrderBy("id")
    List<Account> accList = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "first_name")
    private String first_name;
    @Column(name = "last_name")
    private String last_name;
    @Column(name = "dob")
    private Date dob;
    @Column(name = "country")
    private String country;
    @Column(name = "email")
    private String email;

    public Trader() {
    }

    public List<Account> getAccount() {
        return accList;
    }

    public void setAccList(List<Account> accList) {
        this.accList = accList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Trader{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' + "\n" +
                ", last_name='" + last_name + '\'' + "\n" +
                ", dob=" + dob + "\n" +
                ", country='" + country + '\'' + "\n" +
                ", email='" + email + '\'' + "\n" +
                ", accList=" + accList + "\n" +
                '}';
    }
}
