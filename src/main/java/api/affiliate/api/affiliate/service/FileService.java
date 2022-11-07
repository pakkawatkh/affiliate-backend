package api.affiliate.api.affiliate.service;

import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.exception.FileException;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
@Service

public class FileService {
    public static String uploadDirectory = System.getProperty("user.dir");

    @SneakyThrows
    public String saveImg(MultipartFile file, String imageDir){

        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        this.validateFile(file);
        String contentType = file.getContentType();
        contentType = contentType.substring(contentType.indexOf("/") + 1);
        String image_name = timeStamp + "." + contentType;
        Path fileNameAndPath = Paths.get(uploadDirectory + imageDir, image_name);
        try {
            Files.write(fileNameAndPath, file.getBytes());
        } catch (IOException e) {
            throw FileException.fileNull();
        }
        try {
            file.getBytes();
        } catch (IOException e) {
            throw FileException.fileNull();
        }
        return imageDir + "/" + image_name;
    }


    @SneakyThrows
    public void validateFile(MultipartFile file){
        if (file.isEmpty()) throw FileException.fileNull();
        if (file.getSize() > 1048576 * 5) throw FileException.fileMaxSize();
        if (file.getContentType() == null) throw FileException.unsupported();
    }
}
