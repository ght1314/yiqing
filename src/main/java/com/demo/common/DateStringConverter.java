package com.demo.common;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateStringConverter implements Converter<Date> {
    /**
     * 读的时候用到
     */
    @Override
    public Class supportJavaTypeKey() {
        return Date.class;
    }

    /**
     * 读的时候用到 ， 将excel类型转为Java类型
     */
    @Override
    public Date convertToJavaData(CellData cellData, ExcelContentProperty contentProperty,
                                  GlobalConfiguration globalConfiguration) throws Exception {
        double dateValue = cellData.getNumberValue().doubleValue();
        System.out.println("dateValue :" + dateValue);
        Date time = getNormalDate(false, dateValue);
        System.out.println(time.toLocaleString());
        return time;
    }

    public Date getNormalDate(boolean use1904windowing, double value) {
        int wholeDays = (int) Math.floor(value);
        int millisecondsInDay = (int) ((value - (double) wholeDays) * 8.64E7D + 0.5D);
        Calendar calendar = new GregorianCalendar();
        short startYear = 1900;
        byte dayAdjust = -1;
        if (use1904windowing) {
            startYear = 1904;
            dayAdjust = 1;
        } else if (wholeDays < 61) {
            dayAdjust = 0;
        }
        calendar.set(startYear, 0, wholeDays + dayAdjust, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, millisecondsInDay);
        if (calendar.get(Calendar.MILLISECOND) == 0) {
            calendar.clear(Calendar.MILLISECOND);
        }
        Date date = calendar.getTime();
        return date;
    }


    /**
     * 写的时候用到
     */
    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    /**
     * 写的时候用到，将Java类型转为Excel类型CellData
     */
    @Override
    public CellData convertToExcelData(Date value, ExcelContentProperty contentProperty,
                                       GlobalConfiguration globalConfiguration) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
