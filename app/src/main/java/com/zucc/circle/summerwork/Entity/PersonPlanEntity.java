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
    private Integer schedulegroupid;
    private Integer scheduleparent;
    private String scheduletype;
    private String scheduleremark;
    private List<PersonPlanLogEntity> personPlanLogEntities;

    public PersonPlanEntity(String title, String start_time, String end_time) {
        this.schedulecontent = title;
        this.schedulestart = start_time;
        this.scheduleend = end_time;
    }
    public PersonPlanEntity(){

    }

    public String getScheduleremark() {
        return scheduleremark;
    }

    public void setScheduleremark(String scheduleremark) {
        this.scheduleremark = scheduleremark;
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

    public Integer getSchedulegroupid() {
        return schedulegroupid;
    }

    public void setSchedulegroupid(Integer schedulegroupid) {
        this.schedulegroupid = schedulegroupid;
    }

    public Integer getScheduleparent() {
        return scheduleparent;
    }

    public void setScheduleparent(Integer scheduleparent) {
        this.scheduleparent = scheduleparent;
    }

    public String getScheduletype() {
        return scheduletype;
    }

    public void setScheduletype(String scheduletype) {
        this.scheduletype = scheduletype;
    }

    public List<PersonPlanLogEntity> getPersonPlanLogEntities() {
        return personPlanLogEntities;
    }

    public void setPersonPlanLogEntities(List<PersonPlanLogEntity> personPlanLogEntities) {
        this.personPlanLogEntities = personPlanLogEntities;
    }
}
