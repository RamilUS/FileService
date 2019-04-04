package bell.yusipov.fileservice.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}
 */
@Service
public class EmailServiceImpl implements EmailService{


    private JavaMailSender emailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender emailSender){
        this.emailSender=emailSender;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public  void sendActivationCode(String userEmail, String ActivationCode){

        if(userEmail == null || userEmail.isEmpty()){
            return;
        }
        if(ActivationCode == null || ActivationCode.isEmpty()){
            return;
        }

        SimpleMailMessage message= new SimpleMailMessage();

        String mailText =
                "Подтвердите регистрацию аккаунта  http://localhost:8080/registration/" + ActivationCode;


        message.setSubject("Validation of registration");
        message.setText(mailText);
        message.setTo(userEmail);
        message.setFrom("fileService1313@gmail.com");

        emailSender.send(message);
    }
}
