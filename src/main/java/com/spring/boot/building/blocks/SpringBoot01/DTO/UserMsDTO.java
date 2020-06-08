package com.spring.boot.building.blocks.SpringBoot01.DTO;

public class UserMsDTO {

    private long id;
    private String username;
    private String emailAddress;
    private String roleName;

    public UserMsDTO(long id, String username, String emailAddress, String roleName) {
        this.id = id;
        this.username = username;
        this.emailAddress = emailAddress;
        this.roleName = roleName;
    }

    public UserMsDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
