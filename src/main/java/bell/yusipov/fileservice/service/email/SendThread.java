package bell.yusipov.fileservice.service.email;

/**
 * Поток отправки сообщений
 */
public class SendThread extends Thread {

    private EmailService emailService;
    private String activationCode;
    private String email;

    public SendThread(EmailService emailService, String activationCode, String email) {
        this.emailService = emailService;
        this.activationCode = activationCode;
        this.email = email;
    }

    @Override
    public void run() {
        emailService.sendActivationCode(email, activationCode);
    }
}
