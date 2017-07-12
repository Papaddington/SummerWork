package com.zucc.circle.summerwork.Entity;

import java.util.List;

/**
 * Created by 圆圈 on 2017-07-05.
 */

public class PersonPlanEntity {
    private Integer schedulenumber;
    private String schedulecontent;
    private String schedulestart;
    private String scheduleend;
    private String scheduleuser;
    private Integer schehulegroupid;
    private Integer schehuleparent;
    private String schehuletype;
    private List<PersonPlanLogEntity> personPlanLogEntities;

    public PersonPlanEntity(String title, String start_time, String end_time) {
        this.schedulecontent = title;
        this.schedulestart = start_time;
        this.scheduleend = end_time;
    }
    public PersonPlanEntity(){

    }

    public List<PersonPlanLogEntity> getPersonPlanLogEntities() {
        return personPlanLogEntities;
    }

    public void setPersonPlanLogEntities(List<PersonPlanLogEntity> personPlanLogEntities) {
        this.personPlanLogEntities = personPlanLogEntities;
    }

    public Integer getSchedulenumber() {
        return schedulenumber;
    }

    public void setSchedulenumber(Integer schedulenumber) {
        this.schedulenumber = schedulenumber;
    }

    public String getSchedulecontent() {
        return schedulecontent;
    }

    public void setSchedulecontent(String schedulecontent) {
        this.schedulecontent = schedulecontent;
    }

    public String getSchedulestart() {
        return schedulestart;
    }

    public void setSchedulestart(String schedulestart) {
        this.schedulestart = schedulestart;
    }

    public String getScheduleend() {
        return scheduleend;
    }

    public void setScheduleend(String scheduleend) {
        this.scheduleend = scheduleend;
    }

    public String getScheduleuser() {
        return scheduleuser;
    }

    public void setScheduleuser(String scheduleuser) {
        this.scheduleuser = scheduleuser;
    }

    public Integer getSchehulegroupid() {
        return schehulegroupid;
    }

    public void setSchehulegroupid(Integer schehulegroupid) {
        this.schehulegroupid = schehulegroupid;
    }

    public Integer getSchehuleparent() {
        return schehuleparent;
    }

    public void setSchehuleparent(Integer schehuleparent) {
        this.schehuleparent = schehuleparent;
    }

    public String getSchehuletype() {
        return schehuletype;
    }

    public void setSchehuletype(String schehuletype) {
        this.schehuletype = schehuletype;
    }
}
