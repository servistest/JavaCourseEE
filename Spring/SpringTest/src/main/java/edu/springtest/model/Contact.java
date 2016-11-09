package edu.springtest.model;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Admin on 13.09.2016.
 */
@Entity
//@Table(name = "resttest.contact")
@Table(name = "contact")
//@NamedQuery(
//        name="Contact.findByFirstName",
//        query="SELECT c FROM Contact c WHERE c.firstName LIKE 'Chris' "
//)
@NamedQuery(
        name="Contact.findByFirstName",
        query="SELECT c FROM Contact c WHERE c.firstName LIKE :firstName "
)
public class Contact implements Serializable {
    private Long id;
        private String firstName;
    private String lastName;
    private DateTime birthDate;


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "birth_date")
   @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    public DateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(DateTime birth_date) {
        this.birthDate = birth_date;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("edu.validation.model.Contact{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", birthDate=").append(birthDate);
        sb.append('}');
        return sb.toString();
    }
}

