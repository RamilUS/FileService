package bell.yusipov.fileservice.dao.user;

import bell.yusipov.fileservice.model.Usr;

import java.util.List;

/**
 * Интерфейс  для работы с таблице пользователей
 */
public interface UserDao {
    /**
     * Добавление нового пользователя
     *
     * @param usr - пользователь
     */
    void addUser(Usr usr);

    /**
     * Получение списка всех пользователей
     *
     * @return List список пользователей
     */
    List<Usr> findAll();

    /**
     * Получение пользователя по имени
     *
     * @param usrName имя пользователя
     * @return объект пользователя
     */
    Usr getUserByName(String usrName);

    /**
     * Получение пользователя по его индификатору
     *
     * @param userId индификатору
     * @return объект пользователя
     */
    Usr getUserById(Integer userId);

    /**
     * Получение пользователя по коду активации
     *
     * @param activCode - активационный код
     * @return пользователь
     */
    Usr getUserByActivationCode(String activCode);

    /**
     * Обновление пользователя
     *
     * @param usr пользователь
     */
    void updateUser(Usr usr);

    /**
     * Получение ниндификатора пользователя
     * @param usr пользователь
     * @return индификатор
     */
    Integer getUserId(Usr usr);
}
