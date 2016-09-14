package edu.springhibernate.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Admin on 13.09.2016.
 */
@Entity
@Table(name = "hobby.contact_tel_detail")
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


    @ManyToOne
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ContactTelDetail{");
        sb.append("id=").append(id);
        sb.append(", contact=").append(contact);
        sb.append(", tel_type='").append(tel_type).append('\'');
        sb.append(", tel_number='").append(tel_number).append('\'');
        sb.append(", version=").append(version);
        sb.append('}');
        return sb.toString();
    }
}
