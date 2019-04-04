package bell.yusipov.fileservice.service.file;

import bell.yusipov.fileservice.model.File;
import bell.yusipov.fileservice.model.Usr;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Сервис для работы с файлами
 */
public interface FileService {

    /**
     * Загрузка файла в БД
     *
     * @param uploadFile  файл
     * @param description описание файлв
     * @param fileOwner   собственник файла
     */
    void upload(MultipartFile uploadFile, String description, Usr fileOwner);

    /**
     * Получение файла по названию
     *
     * @param fileName название файла
     * @return файл
     */
    File getByFileName(String fileName);

    /**
     * Получение списка всех файлов
     *
     * @return список всех файлов
     */
    List<File> getFileList();

    /**
     * Получение списка файлов пользователя
     *
     * @param userName имя пользователя
     * @return список файлов пользователя
     */
    List<File> getUserFileList(String userName);

    /**
     * Обновление счетчика скачиваний
     *
     * @param file файл
     */
    void upgradeFileCount(File file);
}
