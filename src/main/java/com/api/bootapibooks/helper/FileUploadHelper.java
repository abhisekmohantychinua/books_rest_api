package com.api.bootapibooks.helper;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadHelper {
    //    public static final String USER_DIR = "D:\\My Codes\\JAVA\\SpringBoot\\bootapibooks\\src\\main\\resources\\static\\images";
    private static String USER_DIR;

    static {
        try {
            USER_DIR = new ClassPathResource("static/images/").getFile().getAbsolutePath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static boolean uploadFile(MultipartFile file) {

        try {
            Files.copy(file.getInputStream(), Paths.get(USER_DIR + File.separator + file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
