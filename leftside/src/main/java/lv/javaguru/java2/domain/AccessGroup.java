package lv.javaguru.java2.domain;

/**
 * Created by Sergey on 19.10.14.
 */
public class AccessGroup {
    private long accessGroupId;
    private String accessGroupName;
    private long userId;
    private long permissionId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(long permissionId) {
        this.permissionId = permissionId;
    }

    public long getAccessGroupId() {
        return accessGroupId;
    }

    public void setAccessGroupId(long accessGroupId) {
        this.accessGroupId = accessGroupId;
    }

    public String getAccessGroupName() {
        return accessGroupName;
    }

    public void setAccessGroupName(String accessGroupName) {
        this.accessGroupName = accessGroupName;
    }
}
