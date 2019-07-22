package ca.jrvs.apps.trading.model.domain;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "trader")
public class Trader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
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

//    @OneToMany(mappedBy="trader_id")
//    List<Account> accList=new ArrayList<>();

    public Trader() {
    }

    public Trader(String first_name, String last_name, Date dob, String country, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.dob = dob;
        this.country = country;
        this.email = email;
    }


//    public List<Account> getAccount() {
//        return accList;
//    }
//
//    public void setAccList(List<Account> accList) {
//        this.accList = accList;
//    }


    @Override
    public String toString() {
        return "Trader{" + "\n" +
                "id=" + id + "\n" +
                ", first_name='" + first_name + '\'' + "\n" +
                ", last_name='" + last_name + '\'' + "\n" +
                ", dob=" + dob + "\n" +
                ", country='" + country + '\'' + "\n" +
                ", email='" + email + '\'' + "\n" +
                '}';
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
}
