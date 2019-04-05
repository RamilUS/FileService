package bell.yusipov.fileservice.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Пользователь
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Служебное поле Hibernate
     */
    @Version
    private Integer version;

    /**
     * Имя
     */
    @Column(name = "user_name", length = 100,nullable = false)
    private String userName;

    /**
     * Электронная почта
     */
    @Column(name = "email", length = 100,nullable = false)
    private String email;

    /**
     * Пароль
     */
    @Column(name = "password", length = 100,nullable = false)
    private String password;

    /**
     * Код активации
     */
    @Column(name = "activ_code", length = 100,nullable = false)
    private String activationCode;

    /**
     * подтвеждение почты
     */
    @Column(name = "is_active")
    private Boolean isActive;


    /**
     * Роль пользователя
     */
    @ManyToOne()
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<File> files;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "request_to_visible_access",
            joinColumns = { @JoinColumn(name = "owner_id") },
            inverseJoinColumns = { @JoinColumn(name = "requester_id") }
    )
    private Set<User> requestersToVisible = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "visible_access",
            joinColumns = { @JoinColumn(name = "owner_id") },
            inverseJoinColumns = { @JoinColumn(name = "requester_id") }
    )
    private Set<User> visibleAccess = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "request_to_download_access",
            joinColumns = { @JoinColumn(name = "owner_id") },
            inverseJoinColumns = { @JoinColumn(name = "requester_id") }
    )
    private Set<User> requestersToDownload = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "download_access",
            joinColumns = { @JoinColumn(name = "owner_id") },
            inverseJoinColumns = { @JoinColumn(name = "requester_id") }
    )
    private Set<User> downloadAccess = new HashSet<>();


    public User() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public Integer getFilesCount(){
        return getFiles().size();
    }

    public Set<User> getRequestersToVisible() {
        return requestersToVisible;
    }

    public void setRequestersToVisible(Set<User> requestersToVisible) {
        this.requestersToVisible = requestersToVisible;
    }

    public Set<User> getVisibleAccess() {
        return visibleAccess;
    }

    public void setVisibleAccess(Set<User> visibleAccess) {
        this.visibleAccess = visibleAccess;
    }

    public Set<User> getRequestersToDownload() {
        return requestersToDownload;
    }

    public void setRequestersToDownload(Set<User> requestersToDownload) {
        this.requestersToDownload = requestersToDownload;
    }

    public Set<User> getDownloadAccess() {
        return downloadAccess;
    }

    public void setDownloadAccess(Set<User> downloadAccess) {
        this.downloadAccess = downloadAccess;
    }
}
