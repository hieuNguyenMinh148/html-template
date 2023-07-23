package com.demo.service.impl;

import com.demo.model.MyFile;
import com.demo.repository.FileRepository;
import com.demo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileRepository repository;

    @Override
    public MyFile findByMyName(String fileName) {
        return repository.findByFileName(fileName);
    }

    @Override
    public MyFile findByUid(int uid) {
        return repository.findByUid(uid);
    }

    @Override
    public void saveFile(MultipartFile file,int uid) {
        MyFile myFile = new MyFile();
        myFile.setUid(uid);
        try {
            myFile.setFileName(file.getOriginalFilename());
            myFile.setContentType(file.getContentType());
            myFile.setContent(file.getBytes());
            repository.save(myFile);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
