package lv.javaguru.java2.domain;

/**
 * Created by SM on 10/18/2014.
 */
public class File {
    private long fileId;
    private long parentFolderId;
    private String fileName;
    private byte extensionId;

    public long getFileId() {
        return fileId;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
    }

    public long getParentFolderId() {
        return parentFolderId;
    }

    public void setParentFolderId(long parentFolderId) {
        this.parentFolderId = parentFolderId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte getExtensionId() {
        return extensionId;
    }

    public void setExtensionId(byte extensionId) {
        this.extensionId = extensionId;
    }
}
