package edu.validation.model;

import org.joda.time.DateTime;

import java.io.Serializable;
import java.net.URL;

/**
 * Created by Admin on 13.09.2016.
 */
//@Entity
//@Table(name = "jta.contact")
public class AnotherContact implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private DateTime birthDate;
    private URL personalSite;

//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

//    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

//    @Column(name = "birth_date")
//    @Temporal(TemporalType.DATE)
    public DateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(DateTime birth_date) {
        this.birthDate = birth_date;
    }

    public URL getPersonalSite() {
        return personalSite;
    }

    public void setPersonalSite(URL personatSite) {
        this.personalSite = personatSite;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("edu.validation.model.Contact{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", birthDate=").append(birthDate);
        sb.append(", personatSite=").append(personalSite);
        sb.append('}');
        return sb.toString();
    }
}
