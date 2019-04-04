package bell.yusipov.fileservice.controller;

import bell.yusipov.fileservice.config.UsrPrincipal;
import bell.yusipov.fileservice.model.File;
import bell.yusipov.fileservice.service.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


/**
 * Контроллер для работы с файлами
 */
@Controller
public class FileControllerImpl {

    private final FileService fileService;

    @Autowired
    public FileControllerImpl(FileService fileService) {

        this.fileService = fileService;

    }

    /**
     * Загрузка файла
     *
     * @param model       модель
     * @param uploadFile  згружаемый файл
     * @param description описание файла
     * @param user        пользователь, загружающий файл
     * @return страницу пользователя
     */
    @PostMapping("/upload")
    public String uploadFile(Model model, @RequestParam("file") MultipartFile uploadFile,
                             @RequestParam("description") String description,
                             @AuthenticationPrincipal UsrPrincipal user) {

        if (description == null || description.isEmpty()) {
            model.addAttribute("report", "Нет описания файла");
            return "userpage";
        }

        if (uploadFile == null) {
            model.addAttribute("report", "Файл не выбран");
        }

        fileService.upload(uploadFile, description, user.getUsr());
        model.addAttribute("report", "Файл загружен");
        model.addAttribute("files", fileService.getFileList());

        return "userpage";
    }

    /**
     * Скачивание файла из БД
     *
     * @param fileName название файла
     * @return скачивание файла
     */
    @GetMapping("/download/{fileName}")
    public ResponseEntity<ByteArrayResource> download(@PathVariable("fileName") String fileName) {

        if (fileName == null || fileName.isEmpty()) {
            throw new RuntimeException("DOWNLOAD ERROR - fine name empty or null");
        }

        File file = fileService.getByFileName(fileName);


        byte[] data = file.getFileData();
        ByteArrayResource resource = new ByteArrayResource(data);
        fileService.upgradeFileCount(file);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getFileName())
                .contentType(MediaType.TEXT_PLAIN)
                .contentLength(data.length)
                .body(resource);
    }

    /**
     * Удаление файла из БД
     *
     * @param fileName название файла
     * @return скачивание файла
     */
    @GetMapping("/remove/{fileName}")
    public String remove(@PathVariable("fileName") String fileName) {

        if (fileName == null || fileName.isEmpty()) {
            throw new RuntimeException("DOWNLOAD ERROR - fine name empty or null");
        }

        File file = fileService.getByFileName(fileName);
        fileService.remove(file);

        return "redirect:/userpage";

    }
}