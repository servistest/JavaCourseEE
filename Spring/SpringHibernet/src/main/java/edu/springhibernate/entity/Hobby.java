package edu.springhibernate.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Admin on 13.09.2016.
 */
@Entity
@Table(name = "contact.hobby")
public class Hobby implements Serializable {
    private Integer hobbyId;
    private Set<Contact> contacts;

    @Id
    @Column(name = "hobby_id")
    public Integer getHobbyId() {
        return hobbyId;
    }

    public void setHobbyId(Integer hobbyId) {
        this.hobbyId = hobbyId;
    }

    @ManyToMany
    @JoinTable(name = "contact.contact_hobby_detail",
            joinColumns =@JoinColumn(name = "hobby_id"),
            inverseJoinColumns = @JoinColumn(name = "contact_id")
    )
    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("edu.springhibernate.entity.Hobby{");
        sb.append("hobbyId=").append(hobbyId);
        sb.append('}');
        return sb.toString();
    }
}
