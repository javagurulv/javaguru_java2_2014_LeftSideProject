package lv.javaguru.java2.domain;

/**
 * Created by SM on 10/23/2014.
 */
public class TodoGroup {
    String name;
    private long groupId;

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
