package lv.javaguru.java2.domain;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Created by SM on 10/18/2014.
 */
@Entity
@Table(name = "files")
public class File implements DomainObject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FileID", columnDefinition = "int(11)")
    private long fileId;
    @Column(name = "Path", length = 500)
    private String path;
    @Column(name = "FileName", length = 40)
    private String fileName;
    @Column(name = "ExtensionID", columnDefinition = "TINYINT", nullable = true)
    private Byte extensionId;
    @Column(name = "TodoItemID", columnDefinition = "int(11)")
    private long todoItemID;
    @Column(name = "UploadDate", columnDefinition = "TIMESTAMP")
    private Calendar UploadDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ItemID", nullable = false)
    private TodoItem todoItem;

    public long getFileId() {
        return fileId;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Byte getExtensionId() {
        return extensionId;
    }

    public void setExtensionId(Byte extensionId) {
        this.extensionId = extensionId;
    }

    public long getTodoItemID() {
        return todoItemID;
    }

    public void setTodoItemID(long todoItemID) {
        this.todoItemID = todoItemID;
    }

    public Calendar getUploadDate() {
        return UploadDate;
    }

    public void setUploadDate(Calendar uploadDate) {
        UploadDate = uploadDate;
    }

    public TodoItem getTodoItem() {
        return this.todoItem;
    }

    @Override
    public void setId(Long id) {
        setFileId(id);
    }
}
