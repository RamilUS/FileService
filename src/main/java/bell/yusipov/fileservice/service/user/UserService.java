package bell.yusipov.fileservice.service.user;

import bell.yusipov.fileservice.model.User;

import java.util.List;

/**
 * Интерфес работы с пользователями
 */
public interface UserService {

    /**
     * Добавление пользователя в БД
     *
     * @param user
     */
    void addUser(User user);

    /**
     * Получения списка пользователей в БД
     *
     * @return список пользователей
     */
    List<User> userList();

    /**
     * Подтверждение почты пользователя
     *
     * @param activationCode код подтверждения
     */
    void validateUser(String activationCode);

    /**
     * Получение собственника страницы по имени собственника
     *
     * @param owner имя собственника
     * @return собственник страницы
     */
    User getOwnerByName(String owner);

    /**
     * Получение индификатора собственника страницы
     * @param user объект собственника
     * @return индификатор
     */
    Integer getOwnerId(User user);
}
