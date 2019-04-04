package bell.yusipov.fileservice.service.file;

import bell.yusipov.fileservice.dao.file.FileDao;
import bell.yusipov.fileservice.dao.user.UserDao;
import bell.yusipov.fileservice.model.File;
import bell.yusipov.fileservice.model.Usr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class FileServiceImpl implements FileService {

    private final UserDao userDao;
    private final FileDao fileDao;

    @Autowired
    public FileServiceImpl(UserDao userDao, FileDao fileDao) {
        this.userDao = userDao;
        this.fileDao = fileDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void upload(MultipartFile uploadFile, String description, Usr fileOwner) {

        if (uploadFile == null || description == null || description.isEmpty() || fileOwner == null) {
            return;
        }

        Usr usr = userDao.getUserByName(fileOwner.getUserName());

        File file = new File();
        file.setDescriprion(description);
        file.setFileName(uploadFile.getOriginalFilename());
        file.setUsr(usr);
        file.setDownloadCount(0);

        try {
            file.setFileData(uploadFile.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        fileDao.uploadFile(file);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public File getByFileName(String fileName) {

        if (fileName == null || fileName.isEmpty()) {
            throw new RuntimeException("File name cannot be null or empty");
        }
        return fileDao.findFileByName(fileName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<File> getFileList() {

        return fileDao.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<File> getUserFileList(String userName) {

        if (userName == null || userName.isEmpty()) {
            throw new RuntimeException("User name cannot be null or empty");
        }

        Usr usr = userDao.getUserByName(userName);
        return fileDao.findUserFiles(usr);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void upgradeFileCount(File file) {
        if (file == null) {
            throw new RuntimeException("File name cannot be null ");
        }
        Integer downloadCount = file.getDownloadCount();
        downloadCount++;
        file.setDownloadCount(downloadCount);

        fileDao.upgradeFile(file);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void remove(File file){
        if (file == null) {
            throw new RuntimeException("File name cannot be null ");
        }

        fileDao.delete(file);

    }
}
