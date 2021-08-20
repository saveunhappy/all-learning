package com.imooc.houjiangtao.alllearning.service.impl;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.imooc.houjiangtao.alllearning.domain.common.PageQuery;
import com.imooc.houjiangtao.alllearning.domain.common.PageResult;
import com.imooc.houjiangtao.alllearning.domain.dto.UserDTO;
import com.imooc.houjiangtao.alllearning.domain.dto.UserExportDTO;
import com.imooc.houjiangtao.alllearning.domain.dto.UserQueryDTO;
import com.imooc.houjiangtao.alllearning.service.ExcelExportService;
import com.imooc.houjiangtao.alllearning.service.FileService;
import com.imooc.houjiangtao.alllearning.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class ExcelExportServiceImpl implements ExcelExportService {
    @Resource(name = "localFileServiceImpl")
    private FileService fileService;
    @Autowired
    private UserService userService;

    /**
     * 执行数据库查询和Excel导出，将数据写入到outputStream中
     *
     * @param outputStream
     * @param queryDTO
     */
    private void export(OutputStream outputStream, UserQueryDTO queryDTO) {
        //1.需要创建一个EasyExcel导出对象
        ExcelWriter exceellWriter = EasyExcelFactory.write(outputStream, UserExportDTO.class).build();
        //2.分批加载数据
        PageQuery<UserQueryDTO> pageQuery = new PageQuery<>();
        pageQuery.setQuery(queryDTO);
        //每页多少条数据
        pageQuery.setPageSize(10);
        int pageNo = 0;
        PageResult<List<UserDTO>> pageResult;

        do {
            pageQuery.setPageNo(++pageNo);
            log.info("开始导出第[{}]页数据",pageNo);
            pageResult = userService.query(pageQuery);
            List<UserExportDTO> userExportDTOList = Optional.ofNullable(pageResult.getData())
                    .map(List::stream)
                    .orElseGet(Stream::empty)
                    .map(userDTO -> {
                        UserExportDTO userExportDTO = new UserExportDTO();
                        BeanUtils.copyProperties(userDTO, userExportDTO);
                        return userExportDTO;
                    }).collect(Collectors.toList());
            //3.导出分批加载的数据

            WriteSheet writeSheet = EasyExcelFactory.writerSheet(pageNo, "第" + pageNo + "页").build();
            exceellWriter.write(userExportDTOList,writeSheet);
            log.info("结束导出第[{}]页数据",pageNo);
            //总页数大于当前页说明还有数据，需要再次执行
        } while (pageResult.getPageNum() > pageNo);

        //4.收尾 ,执行finish，才会关闭excel文件流
        exceellWriter.finish();
        log.info("完成导出");
    }

    @Override
    public void export(UserQueryDTO query, String filename) {
        //输出流
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        //1.实现数据导出到excel中
        export(outputStream, query);
        //输入流
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        //2.实现文件上传
        fileService.upload(inputStream, filename);
    }
}
