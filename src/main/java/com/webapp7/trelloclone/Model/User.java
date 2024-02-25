package com.webapp7.trelloclone.Model;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @Column(unique = true)
    private Long suid;

    private String name;

    private String email;

    public Long getId() {
        return suid;
    }

    public void setId(Long suid) {
        this.suid = suid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
