package com.youtube.clone.service;

import com.youtube.clone.entity.FileEntity;
import com.youtube.clone.repository.FileRepository;
import com.youtube.clone.response.ResponseFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements FileService{
    //**Cargar los videos*/
    @Autowired
    private FileRepository fileRepository;
    @Override
    public FileEntity store(MultipartFile file) throws IOException{
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileEntity fileEntity = FileEntity.builder()
                .name(fileName)
                .type(file.getContentType())
                .data(file.getBytes())
                .build();
        return fileRepository.save(fileEntity);
    }
//**Descargar los videos*/
    @Override
    public Optional<FileEntity> getFile(UUID id) throws FileNotFoundException {
        Optional<FileEntity>file = fileRepository.findById(id);
        if (file.isPresent()){
            return file;
        }
        throw new FileNotFoundException();
    }
//**Ver la lista de los videos y crear la URL*/
    @Override
    public List<ResponseFile> getAllFiles() {
        List<ResponseFile>files = fileRepository.findAll().stream().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("api/fileManager/files/")
                    .path(dbFile.getId().toString())
                    .toUriString();
            return ResponseFile.builder()
                    .name(dbFile.getName())
                    .url(fileDownloadUri)
                    .type(dbFile.getType())
                    .size(dbFile.getData().length).build();
        }).collect(Collectors.toList());
        return files;
    }
}