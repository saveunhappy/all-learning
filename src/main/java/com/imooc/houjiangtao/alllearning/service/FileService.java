package com.imooc.houjiangtao.alllearning.service;

import java.io.File;
import java.io.InputStream;

public interface FileService {
    /**
     * 文件上传
     */
    void upload(InputStream inputStream,String fileName);

    void upload(File file);
}
