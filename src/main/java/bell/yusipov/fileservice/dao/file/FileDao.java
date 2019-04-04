package bell.yusipov.fileservice.dao.file;

import bell.yusipov.fileservice.model.File;
import bell.yusipov.fileservice.model.Usr;

import java.util.List;

/**
 * Интерфейс работы с файлами в БД
 */
public interface FileDao {

    /**
     * Загрузка файла в БД
     *
     * @param file файл
     */
    void uploadFile(File file);

    /**
     * Получение файла по его имени
     *
     * @param fileName файл
     */
    File findFileByName(String fileName);

    /**
     * Получения списка всех файлов
     *
     * @return список файлов
     */
    List<File> findAll();

    /**
     * Получения списка файлов пользователя
     *
     * @param usr - пользователь
     * @return список файлов пользователя
     */
    List<File> findUserFiles(Usr usr);

    /**
     * Обновление данных о файле
     * @param file файл
     */
    void upgradeFile(File file);

    /**
     * Удаление файла
     * @param file файл
     */
    void delete (File file);

}
