package vn.hoidanit.laptopshop.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;

@Service
public class UploadFileService {

    private final ServletContext servletContext;

    public UploadFileService(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public String handleSaveUploadFile(MultipartFile file, String directory) {
        try {
            byte[] bytes = file.getBytes();
            String rootPath = this.servletContext.getRealPath("/resources/images");
            File rootDirectory = new File(rootPath + File.separator + directory);

            if (rootDirectory.exists()) {
                rootDirectory.mkdirs();
            }

            File serverFile = new File(rootDirectory.getAbsolutePath() + File.separator + System.currentTimeMillis()
                    + "-" + file.getOriginalFilename());

            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
            return file.getOriginalFilename();
        } catch (IOException e) {
            return null;
        }
    }
}
