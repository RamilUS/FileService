package bell.yusipov.fileservice.service.email;

/**
 * Сервис отправки email
 */
public interface EmailService {

    /**
     * Отправка письма с кодом активации
     * @param userEmail - почта пользователя
     * @param code - активационный код
     */
    void sendActivationCode(String userEmail, String code);
}
