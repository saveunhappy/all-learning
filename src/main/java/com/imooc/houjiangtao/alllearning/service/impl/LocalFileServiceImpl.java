package com.imooc.houjiangtao.alllearning.service.impl;

import com.imooc.houjiangtao.alllearning.exception.BusinessException;
import com.imooc.houjiangtao.alllearning.exception.ErrorCodeEnum;
import com.imooc.houjiangtao.alllearning.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;

@Slf4j
@Service("localFileServiceImpl")
public class LocalFileServiceImpl implements FileService {

    private static final String BUCKET = "uploads";
    @Override
    public void upload(InputStream inputStream, String fileName) {
        //拼接文件的存储路径
        String storagePath = BUCKET + File.separator + fileName;
        try(//JDK8 TWR 不能关闭外部资源的
                InputStream innerInputStream = inputStream;
                FileOutputStream fileOutputStream = new FileOutputStream(storagePath);

        ){
        byte[] buffer  = new byte[1024];
        int len;
        while ((len = innerInputStream.read(buffer)) > 0){
            fileOutputStream.write(buffer,0,len);
        }
        fileOutputStream.flush();
        } catch (IOException e) {
            log.error("文件上传失败！",e);
            throw new BusinessException(ErrorCodeEnum.FILE_UPLOAD_FAILURE,e);
        }
    }

    @Override
    public void upload(File file) {
        try {
            upload(new FileInputStream(file), file.getName());
        } catch (Exception e) {
            log.error("文件上传失败",e);
            throw new BusinessException(ErrorCodeEnum.FILE_UPLOAD_FAILURE,e);
        }
    }
}
