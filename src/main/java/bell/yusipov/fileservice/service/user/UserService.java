package bell.yusipov.fileservice.service.user;

import bell.yusipov.fileservice.model.Usr;

import java.util.List;

/**
 * Интерфес работы с пользователями
 */
public interface UserService {

    /**
     * Добавление пользователя в БД
     * @param usr
     */
    void addUser(Usr usr);

    /**
     * Получения списка пользователей в БД
     * @return список пользователей
     */
    List<Usr> userList();

    /**
     * Подтверждение почты пользователя
     * @param activationCode код подтверждения
     */
    void validateUser (String activationCode);
}
