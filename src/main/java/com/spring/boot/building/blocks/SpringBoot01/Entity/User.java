package com.spring.boot.building.blocks.SpringBoot01.Entity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "UserEntity")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_name",length = 50,nullable = false,unique = true)
    @NotEmpty(message = "Username is mandatory field. Please enter username")
    private String username;
    @Column(name = "first_name",length = 50,nullable = false)
    @Size(min = 2,message = "First name should be atleast 2 characters long")
    private String firstName;
    @Column(name="last_name",length = 50,nullable = false)
    private String lastName;
    @Column(name="email",length = 50,nullable = false)
    private String email;
    @Column(name = "role",length = 50,nullable = false)
    private String role;
    @Column(name = "ssn",length = 50,nullable = false,unique = true)
    private String ssn;

    public User() {
    }

    public User(String username, String firstName, String lastName, String email, String role, String ssn) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.ssn = ssn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
}
