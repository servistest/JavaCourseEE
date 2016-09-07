package edu.contact;

import java.io.Serializable;

/**
 * Created by Admin on 06.09.2016.
 */
public class ContactTelDetail implements Serializable{
    private Long id;
    private Long contactId;
    private String telType;
    private String telNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public String getTelType() {
        return telType;
    }

    public void setTelType(String telType) {
        this.telType = telType;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("edu.contact.ContactTelDetail{");
        sb.append("id=").append(id);
        sb.append(", contactId=").append(contactId);
        sb.append(", telType='").append(telType).append('\'');
        sb.append(", telNumber='").append(telNumber).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
