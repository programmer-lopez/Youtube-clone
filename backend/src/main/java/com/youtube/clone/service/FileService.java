package com.youtube.clone.service;

import com.youtube.clone.entity.FileEntity;
import com.youtube.clone.response.ResponseFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public interface FileService {
    //Almacena los Videos a la BD
    FileEntity store(MultipartFile file) throws IOException;
    //Descarga los videos que estan en la BD
    Optional<FileEntity>getFile(UUID id) throws FileNotFoundException;
    //Vista de los videos subidos
    List<ResponseFile>getAllFiles();
}
