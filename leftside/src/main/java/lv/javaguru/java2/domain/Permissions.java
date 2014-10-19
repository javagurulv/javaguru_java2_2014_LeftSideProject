package lv.javaguru.java2.domain;

/**
 * Created by Sergey on 19.10.14.
 */
public class Permissions {
    private long permissionId;
    private byte itemType;
    private long itemId;
    private boolean isAllowedReading;
    private boolean isAllowedWriting;
    private boolean isAllowedUpdating;
    private boolean isAllowedDeleting;

    public long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(long permissionId) {
        this.permissionId = permissionId;
    }

    public byte getItemType() {
        return itemType;
    }

    public void setItemType(byte itemType) {
        this.itemType = itemType;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public boolean isAllowedReading() {
        return isAllowedReading;
    }

    public void setAllowedReading(boolean isAllowedReading) {
        this.isAllowedReading = isAllowedReading;
    }

    public boolean isAllowedWriting() {
        return isAllowedWriting;
    }

    public void setAllowedWriting(boolean isAllowedWriting) {
        this.isAllowedWriting = isAllowedWriting;
    }

    public boolean isAllowedUpdating() {
        return isAllowedUpdating;
    }

    public void setAllowedUpdating(boolean isAllowedUpdating) {
        this.isAllowedUpdating = isAllowedUpdating;
    }

    public boolean isAllowedDeleting() {
        return isAllowedDeleting;
    }

    public void setAllowedDeleting(boolean isAllowedDeleting) {
        this.isAllowedDeleting = isAllowedDeleting;
    }
}
