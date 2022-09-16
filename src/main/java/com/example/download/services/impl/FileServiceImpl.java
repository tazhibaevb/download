package com.example.download.services.impl;

import com.example.download.Utils.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.example.download.services.FileService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    private final Path storagePath;

  @Autowired
  public FileServiceImpl(FileStorageProperties fileStorageProperties){
      storagePath = Paths.get(fileStorageProperties.getUploadDir())
              .toAbsolutePath()
              .normalize();
      if (!Files.exists(storagePath)){
          try {
              Files.createDirectories(storagePath);
          } catch (IOException e) {
              throw new RuntimeException("Cannot create storage!", e);
          }
      }
  }


    @Override
    public String storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String folder = UUID.randomUUID().toString();

        Path path = storagePath.resolve(folder).resolve(fileName);

        try {
            Files.createDirectories(path);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            return folder.concat("/").concat(fileName);
        } catch (IOException e) {
            throw new RuntimeException("Cannot store file!", e);
        }
    }

    @Override
    public Resource getFileByName(String fileName) {
        return null;
    }
}
