package com.zucc.circle.summerwork.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 圆圈 on 2017-07-10.
 */

public class PersonEntity {
    private String userphone;
    private String username;
    private String userwxname;
    private String usermailbox;
    private String userstate;
    private List<PersonPlanEntity> personPlanEntities;

    public PersonEntity(String username){
        this.username = username;
        this.personPlanEntities = new ArrayList<>();
    }

    public List<PersonPlanEntity> getPersonPlanEntities() {
        return personPlanEntities;
    }

    public void setPersonPlanEntities(List<PersonPlanEntity> personPlanEntities) {
        this.personPlanEntities = personPlanEntities;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserwxname() {
        return userwxname;
    }

    public void setUserwxname(String userwxname) {
        this.userwxname = userwxname;
    }

    public String getUsermailbox() {
        return usermailbox;
    }

    public void setUsermailbox(String usermailbox) {
        this.usermailbox = usermailbox;
    }

    public String getUserstate() {
        return userstate;
    }

    public void setUserstate(String userstate) {
        this.userstate = userstate;
    }
}
