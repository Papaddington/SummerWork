package com.zucc.circle.summerwork.Entity;

/**
 * Created by 圆圈 on 2017-07-10.
 */

public class PersonEntity {
    private String email;
    private String phoneNumber;
    private String name;
    public PersonEntity(String personName) {
        this.name = personName;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
