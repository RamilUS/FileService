package bell.yusipov.fileservice.dao.role;

import bell.yusipov.fileservice.model.Role;

/**
 * Интерфейс  для работы с таблице ролей
 */
public interface RoleDao {

    /**
     * Получение объекта роли по названию
     * @param roleName - название роли
     * @return - объект Role
     */
    Role getRole (String roleName);
}
