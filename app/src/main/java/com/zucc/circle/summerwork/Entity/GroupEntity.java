package com.zucc.circle.summerwork.Entity;

import java.util.List;

/**
 * Created by 圆圈 on 2017-07-10.
 */

public class GroupEntity {
    private String groupName;
    private PersonEntity groupLeader;
    private List<PersonEntity> groupPeople;
    public GroupEntity(String groupName, PersonEntity groupLeader, List<PersonEntity>  groupPeople) {
        this.groupName = groupName;
        this.groupLeader = groupLeader;
        this.groupPeople = groupPeople;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public PersonEntity getGroupLeader() {
        return groupLeader;
    }

    public void setGroupLeader(PersonEntity groupLeader) {
        this.groupLeader = groupLeader;
    }

    public List<PersonEntity> getGroupPeople() {
        return groupPeople;
    }

    public void setGroupPeople(List<PersonEntity> groupPeople) {
        this.groupPeople = groupPeople;
    }
}
