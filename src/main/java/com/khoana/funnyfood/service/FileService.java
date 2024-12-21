package com.khoana.funnyfood.service;

import com.khoana.funnyfood.service.imp.FileServiceImp;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileService implements FileServiceImp {
    @Value("${fileUpload.rootPath}")
    private String rootPath;
    private Path root ;

    // Tao Folder de luu file
    private void init(){
        try {
            root = Paths.get(rootPath);
            if(Files.notExists(root)){
                    Files.createDirectories(root);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean saveFile(MultipartFile file) {
        try {
            init();
            Files.copy(file.getInputStream(), root.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING); // Laays file input luu vaof folder
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Resource loadFile(String fileName) {
        init();
        try {
            Path file = root.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()){
                return resource;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
