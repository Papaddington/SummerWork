package com.zucc.circle.summerwork.Entity;

import java.util.Date;

/**
 * Created by yuritian on 2017/7/12.
 */

public class PersonPlanLogEntity {
    private Integer logid;
    private String logwrite;
    private String logwriter;
    private String logdata;
    private Integer logscheduleid;

    public Integer getLogid() {
        return logid;
    }

    public void setLogid(Integer logid) {
        this.logid = logid;
    }

    public String getLogwrite() {
        return logwrite;
    }

    public void setLogwrite(String logwrite) {
        this.logwrite = logwrite;
    }

    public String getLogwriter() {
        return logwriter;
    }

    public void setLogwriter(String logwriter) {
        this.logwriter = logwriter;
    }

    public String getLogdata() {
        return logdata;
    }

    public void setLogdata(String logdata) {
        this.logdata = logdata;
    }

    public Integer getLogscheduleid() {
        return logscheduleid;
    }

    public void setLogscheduleid(Integer logscheduleid) {
        this.logscheduleid = logscheduleid;
    }
}
