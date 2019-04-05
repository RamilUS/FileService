package bell.yusipov.fileservice.controller;

import bell.yusipov.fileservice.config.UserPrincipal;
import bell.yusipov.fileservice.service.access.AccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Контроллер запроса доступа
 */
@Controller
public class AccessController {


    private final AccessService accessService;

    @Autowired
    AccessController(AccessService accessService) {

        this.accessService = accessService;
    }

    /**
     * Отправка запроса на просмотр списка файлов
     *
     * @param userPrincipal активный пользователь
     * @param userId       индификатор владельца файлов
     * @return возвращает на страницу активного пользователя
     */
    @GetMapping("/requestvisible/{pageOwnerId}")
    public String requestVisible(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                 @PathVariable("pageOwnerId") Integer userId) {

        accessService.requestVisibleAccess(userPrincipal, userId);

        return "redirect:/userpage";
    }

    /**
     * Подтверждение запроса на просмотр списка файлов
     *
     * @param userPrincipal активный пользователь
     * @param userId       индификатор владельца файлов
     * @return возвращает на страницу активного пользователя
     */
    @GetMapping("/confirmvisible/{pageOwnerId}")
    public String confirmVisible(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                 @PathVariable("pageOwnerId") Integer userId) {
        accessService.confirmVisibleAccess(userPrincipal, userId);

        return "redirect:/userpage";
    }

    /**
     * Отказ запроса на просмотр списка файлов
     * @param userPrincipal активный пользователь
     * @param userId       индификатор владельца файлов
     * @return возвращает на страницу активного пользователя
     */
    @GetMapping("/denyvisible/{pageOwnerId}")
    public String denyVisible(@AuthenticationPrincipal UserPrincipal userPrincipal,
                              @PathVariable("pageOwnerId") Integer userId) {
        accessService.refuseVisibleAccess(userPrincipal, userId);

        return "redirect:/userpage";
    }


    /**
     * Отправка запроса на скачивание файлов
     * @param userPrincipal активный пользователь
     * @param userId       индификатор владельца файлов
     * @return возвращает на страницу активного пользователя
     */
    @GetMapping("/requestdownload/{pageOwnerId}")
    public String requestDownload(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                  @PathVariable("pageOwnerId") Integer userId) {

        accessService.requestDownloadAccess(userPrincipal, userId);

        return "redirect:/userpage";
    }

    /**
     * Подтверждение запроса на скачивание файлов
     * @param userPrincipal активный пользователь
     * @param userId       индификатор владельца файлов
     * @return возвращает на страницу активного пользователя
     */
    @GetMapping("/confirmdownload/{pageOwnerId}")
    public String confirmDownload(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                  @PathVariable("pageOwnerId") Integer userId) {

        accessService.confirmDownloadAccess(userPrincipal, userId);

        return "redirect:/userpage";
    }

    /**
     * Отказ запроса на скачивание файлов
     * @param userPrincipal активный пользователь
     * @param userId       индификатор владельца файлов
     * @return возвращает на страницу активного пользователя
     */
    @GetMapping("/denydownload/{pageOwnerId}")
    public String denyDownload(@AuthenticationPrincipal UserPrincipal userPrincipal,
                               @PathVariable("pageOwnerId") Integer userId) {
        accessService.refuseDownloadAccess(userPrincipal, userId);

        return "redirect:/userpage";
    }

}
