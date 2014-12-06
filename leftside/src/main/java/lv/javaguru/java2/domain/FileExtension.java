package lv.javaguru.java2.domain;

import javax.persistence.*;

/**
 * Created by SM on 10/18/2014.
 */
@Entity
@Table(name = "fileExtensions")
public class FileExtension {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ExtensionID", columnDefinition = "int(11)")
    private byte extensionId;
    @Column(name = "Extension", length = 4)
    private String extension;

    public byte getExtensionId() {
        return extensionId;
    }

    public void setExtensionId(byte extensionId) {
        this.extensionId = extensionId;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
