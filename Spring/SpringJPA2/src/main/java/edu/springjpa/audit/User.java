package edu.springjpa.audit;

import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Admin on 20.09.2016.
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "hobby.user_audit")
public class User extends AbstractAuditable<User,Long> {



    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)

    private Long id;

    private Integer version;
    private String name;




    @Override
    @Column(name = "created_by")
    public User getCreatedBy() {
        return super.getCreatedBy();
    }

    @Override
    public void setCreatedBy(User createdBy) {
        super.setCreatedBy(createdBy);
    }

    @Override
    @Column(name = "created_date")
    public DateTime getCreatedDate() {return super.getCreatedDate();
    }
    @Override
    public void setCreatedDate(DateTime createdDate) {
        super.setCreatedDate(createdDate);
    }

    @Override
    @Column(name = "last_modified_by")
    public User getLastModifiedBy() {
        return super.getLastModifiedBy();
    }

    @Override
    public void setLastModifiedBy(User lastModifiedBy) {
        super.setLastModifiedBy(lastModifiedBy);
    }


    @Override
    @Column(name = "last_modified_date")
    public DateTime getLastModifiedDate() {
        return super.getLastModifiedDate();
    }

    @Override
    public void setLastModifiedDate(DateTime lastModifiedDate) {
        super.setLastModifiedDate(lastModifiedDate);
    }


    @Column(name = "id")
    public Long getId() {
        return super.getId();
    }


    @Override
    protected void setId(Long id) {
        super.setId(id);
    }



    @Override
    public String toString() {
        return super.toString();
    }

    @Version
    @Column(name = "VERSION")
    public int getVersion() {
        return this.version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Column(name = "NAME")
    public String getName() {
        return this.name;
    }

    public void setName(String firstName) {
        this.name = firstName;
    }

}
