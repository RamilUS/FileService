package bell.yusipov.fileservice.controller;

import bell.yusipov.fileservice.config.UsrPrincipal;
import bell.yusipov.fileservice.service.file.FileService;
import bell.yusipov.fileservice.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Основной контроллер
 */
@Controller
public class GeneralControllerImpl  {

    private final UserService userService;
    private final FileService fileService;

    @Autowired
    GeneralControllerImpl(UserService userService, FileService fileService) {

        this.userService = userService;
        this.fileService = fileService;
    }

    /**
     * Переход на главную страницу сервиса
     * @param user действующий пользователь
     * @param model модель
     * @return предстовление остновной страницы сервиса
     */

    @GetMapping("/userpage")
    public String service(@AuthenticationPrincipal UsrPrincipal user, Model model) {

        return "redirect:/userpage/" + user.getUsername();
    }

    /**
     * Переход на страницу пользователя
     * @param usrPrincipal активный пользователь
     * @param pageOwner собственник страницы
     * @param model модель
     * @return представление страницы пользователя
     */

    @GetMapping("/userpage/{pageOwner}")
    public String userPage(@AuthenticationPrincipal UsrPrincipal usrPrincipal,
                           @PathVariable("pageOwner") String pageOwner, Model model) {

        model.addAttribute("files", fileService.getUserFileList(usrPrincipal.getUsername()));
        model.addAttribute("roleName", usrPrincipal.getUsr().getRole().getRoleName());
        model.addAttribute("pageOwner", pageOwner);
        model.addAttribute("userName", usrPrincipal.getUsr().getUserName());
        return "userpage";
    }

    /**
     * Переход на станицу со списком пользователей
     * @param usrPrincipal активный пользователь
     * @param model модель
     * @return представление списка пользователей
     */
    @GetMapping("/userlist")
    public String userList(@AuthenticationPrincipal UsrPrincipal usrPrincipal, Model model) {

        if (model == null) {
            throw new RuntimeException("Ошибка общего контроллера");
        }

        if (usrPrincipal == null || usrPrincipal.getUsr() == null) {
            throw new RuntimeException("Ошибка user principal");
        }

        model.addAttribute("roleName", usrPrincipal.getUsr().getRole().getRoleName());
        model.addAttribute("users", userService.userList());
        model.addAttribute("userName", usrPrincipal.getUsr().getUserName());

        return "userlist";
    }

    /**
     * Переход на домашнюю страницу
     * @return домашняя страница
     */
    @GetMapping("/")
    public String home() {
        return "home";
    }

}
