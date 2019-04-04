package bell.yusipov.fileservice.controller;


import bell.yusipov.fileservice.model.Usr;
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
public class RegistrationControllerImpl {

    private final UserService userService;

    @Autowired
    RegistrationControllerImpl(UserService userService) {
        this.userService = userService;
    }

    /**
     * Переход на страницу регистрации
     * @param usr пустой объект пользователя
     * @return имя представления регистрации
     */
    @GetMapping("/registration")
    public String registration(Usr usr) {

        return "registration";
    }

    /**
     * Добавление нового пользователя
     * @param usr данные пользователя
     * @param result объект перехвата ошибок
     * @return перенаправляет на страницу логина или обновляет страницу регистрации в случае ошибки
     */
    @PostMapping("/registration")
    public String addUser(Usr usr, BindingResult result) {

        if (usr == null) {
            return "login";
        }

        if (result.hasErrors()) {
            return "registration";
        }

        userService.addUser(usr);

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
