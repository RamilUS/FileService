package bell.yusipov.fileservice.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Объект роли пользователя
 */
@Entity
@Table(name = "role")
public class Role implements Serializable{

    /**
     * Идетнификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Название роли
     */
    @Column(name = "role_name", length = 20)
    private String roleName;

    /**
     * Пользователи с данной ролью
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private List<Usr> users;

    public Role(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Usr> getUsers() {
        return users;
    }

    public void setUsers(List<Usr> users) {
        this.users = users;
    }
}
