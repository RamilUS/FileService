package bell.yusipov.fileservice.controller;

import bell.yusipov.fileservice.config.UsrPrincipal;
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
     * @param usrPrincipal активный пользователь
     * @param userId       индификатор владельца файлов
     * @return возвращает на страницу активного пользователя
     */
    @GetMapping("/requestvisible/{pageOwnerId}")
    public String requestVisible(@AuthenticationPrincipal UsrPrincipal usrPrincipal,
                                 @PathVariable("pageOwnerId") Integer userId) {

        accessService.requestVisibleAccess(usrPrincipal, userId);

        return "redirect:/userpage";
    }

    /**
     * Подтверждение запроса на просмотр списка файлов
     *
     * @param usrPrincipal активный пользователь
     * @param userId       индификатор владельца файлов
     * @return возвращает на страницу активного пользователя
     */
    @GetMapping("/confirmvisible/{pageOwnerId}")
    public String confirmVisible(@AuthenticationPrincipal UsrPrincipal usrPrincipal,
                                 @PathVariable("pageOwnerId") Integer userId) {
        accessService.confirmVisibleAccess(usrPrincipal, userId);

        return "redirect:/userpage";
    }

    /**
     * Отказ запроса на просмотр списка файлов
     * @param usrPrincipal активный пользователь
     * @param userId       индификатор владельца файлов
     * @return возвращает на страницу активного пользователя
     */
    @GetMapping("/denyvisible/{pageOwnerId}")
    public String denyVisible(@AuthenticationPrincipal UsrPrincipal usrPrincipal,
                              @PathVariable("pageOwnerId") Integer userId) {
        accessService.refuseVisibleAccess(usrPrincipal, userId);

        return "redirect:/userpage";
    }


    /**
     * Отправка запроса на скачивание файлов
     * @param usrPrincipal активный пользователь
     * @param userId       индификатор владельца файлов
     * @return возвращает на страницу активного пользователя
     */
    @GetMapping("/requestdownload/{pageOwnerId}")
    public String requestDownload(@AuthenticationPrincipal UsrPrincipal usrPrincipal,
                                  @PathVariable("pageOwnerId") Integer userId) {

        accessService.requestDownloadAccess(usrPrincipal, userId);

        return "redirect:/userpage";
    }

    /**
     * Подтверждение запроса на скачивание файлов
     * @param usrPrincipal активный пользователь
     * @param userId       индификатор владельца файлов
     * @return возвращает на страницу активного пользователя
     */
    @GetMapping("/confirmdownload/{pageOwnerId}")
    public String confirmDownload(@AuthenticationPrincipal UsrPrincipal usrPrincipal,
                                  @PathVariable("pageOwnerId") Integer userId) {

        accessService.confirmDownloadAccess(usrPrincipal, userId);

        return "redirect:/userpage";
    }

    /**
     * Отказ запроса на скачивание файлов
     * @param usrPrincipal активный пользователь
     * @param userId       индификатор владельца файлов
     * @return возвращает на страницу активного пользователя
     */
    @GetMapping("/denydownload/{pageOwnerId}")
    public String denyDownload(@AuthenticationPrincipal UsrPrincipal usrPrincipal,
                               @PathVariable("pageOwnerId") Integer userId) {
        accessService.refuseDownloadAccess(usrPrincipal, userId);

        return "redirect:/userpage";
    }

}
