package com.imooc.houjiangtao.alllearning.service;

import com.imooc.houjiangtao.alllearning.domain.dto.UserQueryDTO;

public interface ExcelExportService {
    void export(UserQueryDTO userDTO,String filename);
}
