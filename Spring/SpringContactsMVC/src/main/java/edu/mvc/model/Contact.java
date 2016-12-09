package edu.mvc.model;




import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ALex on 13.11.2016.
 */
@Entity
public class Contact implements Serializable {
    @AssertTrue(message = "should by First Name and Last name")
    public boolean isShouldFirstNameAndLastName(){
        Boolean result=true;
        if(firstName==null || lastName==null){
            result=false;
        }
        return result;
    }
    private static final String DATE_FORMAT="yyyy-MM-dd";
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String description;
    byte[] photo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    // message = "{validation.firstname.NotEmpty.message} - приводит к извлечению  сообщений из пакета ресурсов
    // для интернализации сообщений
    @NotEmpty(message="{NotEmpty.contact.firstName}")
    @Size(min = 3,max = 60,message = "{Size.contact.firstName}")
    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotEmpty(message = "{NotEmpty.contact.lastName}")
    @Size(min = 2,max = 40,message = "{Size.contact.lastName}")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    //conversion String to  java.util.Date
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getDataTimeString(){
        String birdTimeString="";
        if(birthDate!=null){
            birdTimeString=new  SimpleDateFormat(DATE_FORMAT).format(birthDate);
        }
        return birdTimeString;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", description='" + description + '\'' +
                '}';
    }
}
