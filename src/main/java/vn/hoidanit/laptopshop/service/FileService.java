package vn.hoidanit.laptopshop.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;

@Service
public class FileService {

    private final ServletContext servletContext;

    public FileService(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public String handleSaveUploadFile(MultipartFile file, String directory) {
        String fileName = "";
        try {
            byte[] bytes = file.getBytes();
            String rootPath = this.servletContext.getRealPath("/resources/images");
            File rootDirectory = new File(rootPath + File.separator + directory);

            if (rootDirectory.exists()) {
                rootDirectory.mkdirs();
            }

            fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
            File serverFile = new File(rootDirectory.getAbsolutePath() + File.separator + fileName);

            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
            return fileName;
        } catch (IOException e) {
            return null;
        }
    }

    public Boolean handleDeleteUploadFile(String fileName, String directory) {
        String rootPath = this.servletContext.getRealPath("/resources/images");
        File dir = new File(rootPath + File.separator + directory);

        if (dir.exists()) {
            dir.mkdirs();
        }

        File currentFile = new File(dir.getAbsolutePath() + File.separator + fileName);
        Boolean check = currentFile.delete();
        return check;
    }
}
