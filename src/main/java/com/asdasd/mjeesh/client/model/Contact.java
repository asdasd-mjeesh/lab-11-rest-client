package com.asdasd.mjeesh.client.model;

public class Contact {
    private Long id;
    private Long number;

    public Contact() {  }

    public Contact(Long id, Long number) {
        this.id = id;
        this.number = number;
    }

    @Override
    public String toString() {
        return "Contact{" +
               "id=" + id +
               ", number=" + number +
               '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }
}
