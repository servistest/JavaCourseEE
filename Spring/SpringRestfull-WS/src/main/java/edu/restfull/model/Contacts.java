package edu.restfull.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ALex on 09.10.2016.
 */
public class Contacts implements Serializable {
    private ArrayList<Contact> contacts;

    public Contacts() {
    }

    public Contacts(ArrayList<Contact> contactList){
        this.contacts=contactList;
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }
}
