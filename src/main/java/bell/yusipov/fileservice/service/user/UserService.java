package bell.yusipov.fileservice.service.user;

import bell.yusipov.fileservice.model.Usr;

import java.util.List;

/**
 * Интерфес работы с пользователями
 */
public interface UserService {

    /**
     * Добавление пользователя в БД
     *
     * @param usr
     */
    void addUser(Usr usr);

    /**
     * Получения списка пользователей в БД
     *
     * @return список пользователей
     */
    List<Usr> userList();

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
    Usr getOwnerByName(String owner);

    /**
     * Получение индификатора собственника страницы
     * @param usr объект собственника
     * @return индификатор
     */
    Integer getOwnerId(Usr usr);
}
