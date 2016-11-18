package edu.mvc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 17.11.2016.
 */
public class Contacts implements Serializable {
    private ArrayList<Contact> contacts;
    public Contacts() {
    }

    public Contacts(ArrayList<Contact> contacts){
        this.contacts=contacts;
    }

    public ArrayList<Contact> getContacts() {
        return this.contacts;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }
}
