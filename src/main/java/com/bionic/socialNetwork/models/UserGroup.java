package com.bionic.socialNetwork.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * UserGroup entity
 *
 * @author yoalex5
 * @version 1.00 16.07.14
 */
@Entity
@Table(name = "Users_Groups")
public class UserGroup {
    @Column(name = "user_id")
    private long userId;

    @Column(name = "group_id")
    private long groupId;

    public UserGroup () {

    }

    public UserGroup(long userId, long groupId) {
        this.userId = userId;
        this.groupId = groupId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserGroup userGroup = (UserGroup) o;

        if (groupId != userGroup.groupId) return false;
        if (userId != userGroup.userId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) (groupId ^ (groupId >>> 32));
        return result;
    }
}
