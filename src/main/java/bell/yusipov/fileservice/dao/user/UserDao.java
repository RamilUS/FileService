package bell.yusipov.fileservice.dao.user;

import bell.yusipov.fileservice.model.User;

import java.util.List;

/**
 * Интерфейс  для работы с таблице пользователей
 */
public interface UserDao {
    /**
     * Добавление нового пользователя
     *
     * @param user - пользователь
     */
    void addUser(User user);

    /**
     * Получение списка всех пользователей
     *
     * @return List список пользователей
     */
    List<User> findAll();

    /**
     * Получение пользователя по имени
     *
     * @param usrName имя пользователя
     * @return объект пользователя
     */
    User getUserByName(String usrName);

    /**
     * Получение пользователя по его индификатору
     *
     * @param userId индификатору
     * @return объект пользователя
     */
    User getUserById(Integer userId);

    /**
     * Получение пользователя по коду активации
     *
     * @param activCode - активационный код
     * @return пользователь
     */
    User getUserByActivationCode(String activCode);

    /**
     * Обновление пользователя
     *
     * @param user пользователь
     */
    void updateUser(User user);

    /**
     * Получение ниндификатора пользователя
     * @param user пользователь
     * @return индификатор
     */
    Integer getUserId(User user);
}
