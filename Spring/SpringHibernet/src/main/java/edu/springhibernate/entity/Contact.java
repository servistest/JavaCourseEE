package edu.springhibernate.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Admin on 13.09.2016.
 */
@Entity
@Table(name = "hobby.contact")
@NamedQueries({
        @NamedQuery(name = "Contact.findAllWithDetail",
                query = "select distinct c from Contact c " +
                        "left join fetch c.contactTelDetails t left join fetch c.hobbies h"),
        @NamedQuery(name = "Contact.findById",
                query = "select distinct c from Contact c  " +
                        "left join fetch c.contactTelDetails t left join fetch c.hobbies h where c.id=:id"
        )
})
public class Contact implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private Integer version;
    private Set<ContactTelDetail> contactTelDetails=new HashSet<ContactTelDetail>();
    private Set<Hobby> hobbies=new HashSet<Hobby>();

    public Contact() {    }

    public Contact(String firstName,String lastName,Date birthDate){
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setBirthDate(birthDate);
    }

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
    @Temporal(TemporalType.DATE)
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birth_date) {
        this.birthDate = birth_date;
    }

    @OneToMany(mappedBy = "contact",cascade = CascadeType.ALL,orphanRemoval = true)
    public Set<ContactTelDetail> getContactTelDetails() {
        return contactTelDetails;
    }

    public void setContactTelDetails(Set<ContactTelDetail> contactTelDetails) {
        this.contactTelDetails = contactTelDetails;
    }
    public void addContactTelDetails(ContactTelDetail contactTelDetail){
        contactTelDetail.setContact(this);
        getContactTelDetails().add(contactTelDetail);
    }

    public void removeContactTelDetails(ContactTelDetail contactTelDetail){
        getContactTelDetails().remove(contactTelDetail);
    }

    @Version
    @Column(name = "version")
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "hobby.contact_hobby_detail",
            joinColumns = @JoinColumn(name = "contact_id"),
            inverseJoinColumns = @JoinColumn(name = "hobby_id")
    )
    public Set<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(Set<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Contact{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", birth_date=").append(birthDate);
        sb.append(", version=").append(version);
        sb.append('}');
        return sb.toString();
    }
}
