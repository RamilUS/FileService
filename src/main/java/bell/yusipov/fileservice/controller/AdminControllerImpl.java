package bell.yusipov.fileservice.controller;


import bell.yusipov.fileservice.service.file.FileService;
import bell.yusipov.fileservice.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Контроллер администратора
 */
@Controller
@PreAuthorize("hasAnyAuthority('admin', 'analyst')")
public class AdminControllerImpl /*implements Admin Controller */{

    private final UserService userService;
    private final FileService fileService;

    @Autowired
    AdminControllerImpl(UserService userService, FileService fileService) {
        this.userService = userService;
        this.fileService = fileService;
    }

    /**
     * Переход на страницу со списком всех файлов
     * @param model модель
     * @return  страница со свиском файлов
     */
    @GetMapping("/filelist")
    public String fileList(Model model) {

        if (model == null) {
            throw new RuntimeException("Admin Controller ERROR - model is null");
        }

        model.addAttribute("files", fileService.getFileList());

        return "filelist";
    }

    /**
     * Переход на страницу со статистикой
     * @param model модель
     * @return страница со статисикой
     */
    @GetMapping("/statistic")
    public String statistic(Model model) {
        if (model == null){
            throw new RuntimeException("Admin page model error");
        }

        model.addAttribute("userCount", userService.userList().size());
        model.addAttribute("fileCount", fileService.getFileList().size());

        return "statistic";
    }
}
