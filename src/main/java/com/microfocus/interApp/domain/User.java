package com.microfocus.interApp.domain;

import javax.persistence.*;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class User implements Entity {

    @Id
    @GeneratedValue()
    private Long id;
    private String name;
    private String mailAccount;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER )
    @JoinColumn(name = "USER_ID")
    private Set<MailAccount> mailAccounts = new HashSet<>();

    public User(){ }

    public User(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMailAccount(String mailAccount) {
        this.mailAccount = mailAccount;
    }

    public void setMailAccounts() {
        this.mailAccounts.add(new MailAccount(mailAccount));
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<MailAccount> getMailAccounts() {
        return mailAccounts;
    }

    public String getMailAccount() {
        return mailAccount;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String name() {
        return null;
    }
}
