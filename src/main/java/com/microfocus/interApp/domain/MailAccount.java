package com.microfocus.interApp.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class MailAccount {

    @Column(unique = true)
    private String fullName;
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private User user;

    public MailAccount() {
    }

    public MailAccount(String fullName) {
        this.fullName = fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public void setUser(User user) {
        this.user = user;
    }

    public String getFullName() {
        return fullName;
    }

    public User getUser() {
        return user;
    }


    @Override
    public String toString() {
        return "MailAccount{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MailAccount that = (MailAccount) o;
        return Objects.equals(fullName, that.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName);
    }

}
