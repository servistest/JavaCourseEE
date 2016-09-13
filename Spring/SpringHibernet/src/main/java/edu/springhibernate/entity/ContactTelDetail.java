package edu.springhibernate.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Admin on 13.09.2016.
 */
@Entity
@Table(name = "contact.contact_tel_detail")
public class ContactTelDetail implements Serializable {
    private Integer id;
    private Contact contact;
    private String tel_type;
    private String tel_number;
    private Integer version;

    public ContactTelDetail() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @ManyToMany
    @JoinColumn(name = "contact_id")
    public Contact getContact() {
        return this.contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Column(name = "tel_type")
    public String getTel_type() {
        return tel_type;
    }

    public void setTel_type(String tel_type) {
        this.tel_type = tel_type;
    }

    @Column(name = "tel_number")
    public String getTel_number() {
        return tel_number;
    }

    public void setTel_number(String tel_number) {
        this.tel_number = tel_number;
    }

    @Version
    @Column(name = "version")
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
