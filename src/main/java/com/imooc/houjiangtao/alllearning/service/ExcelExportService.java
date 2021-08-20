package com.imooc.houjiangtao.alllearning.service;

import com.imooc.houjiangtao.alllearning.domain.dto.UserQueryDTO;

public interface ExcelExportService {
    /**
     * 同步导出
     * @param userDTO
     * @param filename
     */
    void export(UserQueryDTO userDTO,String filename);

    /**
     * 异步导出
     * @param userDTO
     * @param filename
     */
    void asyncExport(UserQueryDTO userDTO,String filename);


}
