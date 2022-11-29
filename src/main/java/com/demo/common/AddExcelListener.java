package com.demo.common;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.demo.service.AddressService;
import com.demo.dto.AddressDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class AddExcelListener extends AnalysisEventListener<AddressDto> {


    private static final Logger LOGGER = LoggerFactory.getLogger(AddExcelListener.class);
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    /**
     * 实现业务逻辑的service
     */
    private final AddressService addressService;
    /**
     * 保存数据的集合
     */
    List<AddressDto> list = new ArrayList<AddressDto>();

    /**
     * @param residentService
     */
    public AddExcelListener(AddressService addressService) {
        this.addressService = addressService;
    }

    @Override
    public void invoke(AddressDto data, AnalysisContext context) {
        list.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        LOGGER.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        int batchResident = addressService.insertBatchAddress(list);
        if (batchResident < 1) {
            LOGGER.info("数据保存异常，未知错误");
        }
    }

}
