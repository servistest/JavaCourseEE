package com.apress.prospring4.ch8;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import org.hibernate.annotations.Type;
import org.springframework.data.domain.Auditable;
import org.joda.time.DateTime;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "audit.contact_audit")
//PSQLException: ОШИБКА: нулевое значение в столбце "id" нарушает ограничение NOT NULL - при сохранении нового контакат
//Такая ошибка при напрвильном диалекте указанном для хибернейта: должно org.hibernate.dialect.PostgreSQL95Dialect
public class ContactAudit implements Auditable<String, Long>, Serializable {
    private Long id;
    private int version;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String createdBy;
    private DateTime createdDate;
    private String lastModifiedBy;
    private DateTime lastModifiedDate;


//    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "sequenceGenerator")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    @Version
    @Column(name = "VERSION")
    public int getVersion() {
        return this.version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Column(name = "FIRST_NAME")
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "LAST_NAME")
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTH_DATE")
    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Column(name="CREATED_BY")
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Column(name="CREATED_DATE")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    public DateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(DateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Column(name="LAST_MODIFIED_BY")
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Column(name="LAST_MODIFIED_DATE")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    public DateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(DateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Transient
    public boolean isNew() {
        if (id == null) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return "Contact - Id: " + id + ", First name: " + firstName
            + ", Last name: " + lastName + ", Birthday: " + birthDate
            + ", Create by: " + createdBy + ", Create date: " + createdDate
            + ", Modified by: " + lastModifiedBy + ", Modified date: " 
            + lastModifiedDate;
    }
}
