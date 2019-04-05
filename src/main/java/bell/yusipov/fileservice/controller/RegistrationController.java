package bell.yusipov.fileservice.controller;


import bell.yusipov.fileservice.model.User;
import bell.yusipov.fileservice.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Контроллер регистрации
 */
@Controller
public class RegistrationController {

    private final UserService userService;

    @Autowired
    RegistrationController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Переход на страницу регистрации
     * @param user пустой объект пользователя
     * @return имя представления регистрации
     */
    @GetMapping("/registration")
    public String registration(User user) {

        return "registration";
    }

    /**
     * Добавление нового пользователя
     * @param user данные пользователя
     * @param result объект перехвата ошибок
     * @return перенаправляет на страницу логина или обновляет страницу регистрации в случае ошибки
     */
    @PostMapping("/registration")
    public String addUser(User user, BindingResult result) {

        if (user == null) {
            return "login";
        }

        if (result.hasErrors()) {
            return "registration";
        }

        userService.addUser(user);

        return "redirect:/login";
    }

    /**
     * Активация пользователя по ссылке из письма
     * @param activationCode активационный код
     * @return перенаправляет на страницу логина
     */
    @GetMapping("/registration/{activationCode}")
    public String activation(@PathVariable("activationCode") String activationCode) {

        if (activationCode==null || activationCode.isEmpty()){
            throw new RuntimeException("ActivationCode is null or empty");
        }

        userService.validateUser(activationCode);

        return "redirect:/login";

    }
}
