package edu.springhibernate.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Admin on 13.09.2016.
 */
@Entity
@Table(name = "hobby.hobby")
public class Hobby implements Serializable {
    private String hobbyId;
    private Set<Contact> contacts;

    @Id
    @Column(name = "hobby_id")
    public String getHobbyId() {
        return hobbyId;
    }

    public void setHobbyId(String hobbyId) {
        this.hobbyId = hobbyId;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "hobby.contact_hobby_detail",
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
        final StringBuilder sb = new StringBuilder("Hobby{");
        sb.append("hobbyId=").append(hobbyId);
        sb.append('}');
        return sb.toString();
    }
}
