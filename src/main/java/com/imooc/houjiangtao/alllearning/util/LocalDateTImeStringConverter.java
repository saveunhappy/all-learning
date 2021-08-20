package com.imooc.houjiangtao.alllearning.util;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTImeStringConverter implements Converter<LocalDateTime> {
    @Override
    public Class supportJavaTypeKey() {
        return LocalDateTime.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    /**
     * 导入时使用
     * @param cellData
     * @param excelContentProperty
     * @param globalConfiguration
     * @return
     * @throws Exception
     */
    @Override
    public LocalDateTime convertToJavaData(CellData cellData, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return null;
    }

    /**
     * 导出时使用
     * @param localDateTime
     * @param excelContentProperty
     * @param globalConfiguration
     * @return
     * @throws Exception
     */
    @Override
    public CellData convertToExcelData(LocalDateTime localDateTime, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        if(excelContentProperty == null
                || excelContentProperty.getDateTimeFormatProperty() == null){
            return new CellData(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(localDateTime));
        }else {
            return new CellData(DateTimeFormatter.ofPattern(excelContentProperty
            .getDateTimeFormatProperty().getFormat()));
        }
    }
}
