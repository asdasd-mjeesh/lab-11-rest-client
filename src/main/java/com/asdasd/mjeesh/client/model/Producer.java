package com.asdasd.mjeesh.client.model;

import java.util.List;

public class Producer {
    private Long id;
    private String name;
    private List<Contact> contacts;

    public Producer() {  }

    public Producer(Long id, String name, List<Contact> contacts) {
        this.id = id;
        this.name = name;
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "Producer{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", contacts=" + contacts +
               '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
