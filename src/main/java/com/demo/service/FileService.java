package com.demo.service;

import com.demo.model.MyFile;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    MyFile findByMyName(String fileName);

    MyFile findByUid(int uid);

    void saveFile(MultipartFile file, int uid);

}
