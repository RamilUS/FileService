package bell.yusipov.fileservice.service.access;

import bell.yusipov.fileservice.config.UsrPrincipal;

/**
 * Интерфейс для работы с доступом к файлам другого пользователя
 */
public interface AccessService {

    /**
     * Запрос активного пользователя на доступ просмотра файлов
     *
     * @param usrPrincipal активный пользователь
     * @param userId       владелец файлов
     */
    void requestVisibleAccess(UsrPrincipal usrPrincipal, Integer userId);

    /**
     * Подтверждение запроса на доступ к просмотрю файлов
     * @param usrPrincipal активный пользователь
     * @param userId владелец файлов
     */
    void confirmVisibleAccess(UsrPrincipal usrPrincipal, Integer userId);

    /**
     * Отказ апроса на доступ к просмотрю файлов
     * @param usrPrincipal активный пользователь
     * @param userId владелец файлов
     */
    void refuseVisibleAccess(UsrPrincipal usrPrincipal, Integer userId);

    /**
     * Запрос активного пользователя на доступ скачивания файлов
     * @param usrPrincipal активный пользователь
     * @param userId владелец файлов
     */
    void requestDownloadAccess(UsrPrincipal usrPrincipal, Integer userId);

    /**
     * Подтверждение запроса на доступ к скачивание файлов
     * @param usrPrincipal активный пользователь
     * @param userId владелец файлов
     */
    void confirmDownloadAccess(UsrPrincipal usrPrincipal, Integer userId);

    /**
     * Отказ запроса на доступ к просмотрю файлов
     * @param usrPrincipal активный пользователь
     * @param userId владелец файлов
     */
    void refuseDownloadAccess(UsrPrincipal usrPrincipal, Integer userId);

}
