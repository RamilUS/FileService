package bell.yusipov.fileservice.service.access;

import bell.yusipov.fileservice.config.UserPrincipal;

/**
 * Интерфейс для работы с доступом к файлам другого пользователя
 */
public interface AccessService {

    /**
     * Запрос активного пользователя на доступ просмотра файлов
     *
     * @param userPrincipal активный пользователь
     * @param userId       владелец файлов
     */
    void requestVisibleAccess(UserPrincipal userPrincipal, Integer userId);

    /**
     * Подтверждение запроса на доступ к просмотрю файлов
     * @param userPrincipal активный пользователь
     * @param userId владелец файлов
     */
    void confirmVisibleAccess(UserPrincipal userPrincipal, Integer userId);

    /**
     * Отказ апроса на доступ к просмотрю файлов
     * @param userPrincipal активный пользователь
     * @param userId владелец файлов
     */
    void refuseVisibleAccess(UserPrincipal userPrincipal, Integer userId);

    /**
     * Запрос активного пользователя на доступ скачивания файлов
     * @param userPrincipal активный пользователь
     * @param userId владелец файлов
     */
    void requestDownloadAccess(UserPrincipal userPrincipal, Integer userId);

    /**
     * Подтверждение запроса на доступ к скачивание файлов
     * @param userPrincipal активный пользователь
     * @param userId владелец файлов
     */
    void confirmDownloadAccess(UserPrincipal userPrincipal, Integer userId);

    /**
     * Отказ запроса на доступ к просмотрю файлов
     * @param userPrincipal активный пользователь
     * @param userId владелец файлов
     */
    void refuseDownloadAccess(UserPrincipal userPrincipal, Integer userId);

}
