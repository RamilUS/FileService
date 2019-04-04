package bell.yusipov.fileservice.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Файл
 */
@Entity
@Table(name = "file")
public class File implements Serializable{

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Имя файла
     */
    @Column(name = "file_name")
    private String fileName;

    /**
     * Данные файла
     */
    @Column(name = "file_data")
    private byte[] fileData;

    /**
     * Описание
     */
    @Column(name = "description")
    private String descriprion;

    @Column(name = "download_count")
    private Integer downloadCount;

    /**
     * Владелец файла
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Usr usr;

    public File(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public String getDescriprion() {
        return descriprion;
    }

    public void setDescriprion(String descriprion) {
        this.descriprion = descriprion;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public Usr getUsr() {
        return usr;
    }

    public void setUsr(Usr usr) {
        this.usr = usr;
    }
}
