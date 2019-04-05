package bell.yusipov.fileservice.controller;

import bell.yusipov.fileservice.config.UserPrincipal;
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
public class GeneralController {

    private final UserService userService;
    private final FileService fileService;

    @Autowired
    GeneralController(UserService userService, FileService fileService) {

        this.userService = userService;
        this.fileService = fileService;
    }

    /**
     * Переход на главную страницу сервиса действующиго пользователя
     * @param user действующий пользователь
     * @param model модель
     * @return предстовление основной страницы сервиса
     */

    @GetMapping("/userpage")
    public String service(@AuthenticationPrincipal UserPrincipal user, Model model) {

        return "redirect:/userpage/" + user.getUsername();
    }

    /**
     * Переход на страницу пользователя
     * @param userPrincipal активный пользователь
     * @param pageOwner собственник страницы
     * @param model модель
     * @return представление страницы пользователя
     */

    @GetMapping("/userpage/{pageOwner}")
    public String userPage(@AuthenticationPrincipal UserPrincipal userPrincipal,
                           @PathVariable("pageOwner") String pageOwner, Model model) {

        model.addAttribute("files", fileService.getUserFileList(pageOwner));
        model.addAttribute("roleName", userPrincipal.getUser().getRole().getRoleName());
        model.addAttribute("pageOwner", pageOwner);
        model.addAttribute("pageOwnerId", userService.getOwnerId(userService.getOwnerByName(pageOwner)));
        model.addAttribute("userName", userPrincipal.getUser().getUserName());
        return "userpage";
    }

    /**
     * Переход на станицу со списком пользователей
     * @param userPrincipal активный пользователь
     * @param model модель
     * @return представление списка пользователей
     */
    @GetMapping("/userlist")
    public String userList(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {

        if (model == null) {
            throw new RuntimeException("Ошибка общего контроллера");
        }

        if (userPrincipal == null || userPrincipal.getUser() == null) {
            throw new RuntimeException("Ошибка user principal");
        }

        model.addAttribute("roleName", userPrincipal.getUser().getRole().getRoleName());
        model.addAttribute("users", userService.userList());
        model.addAttribute("userName", userPrincipal.getUser().getUserName());
        model.addAttribute("requestUsers", userPrincipal.getUser().getRequestersToVisible());

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
