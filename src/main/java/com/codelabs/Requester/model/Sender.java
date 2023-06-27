package com.codelabs.Requester.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Sender implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String subject;
    private String message;
    private String password;
    private String recipient;

    public Long getId() {
        return id;
    }

    public Sender setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Sender setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Sender setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Sender setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public Sender setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Sender setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getRecipient() {
        return recipient;
    }

    public Sender setRecipient(String recipient) {
        this.recipient = recipient;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Sender setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "Sender{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                ", password='" + password + '\'' +
                ", recipient='" + recipient + '\'' +
                '}';
    }
}
