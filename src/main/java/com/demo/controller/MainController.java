package com.demo.controller;

import com.demo.service.AddressService;
import com.demo.service.RecordService;
import com.demo.service.ResidentService;
import com.demo.service.VisitorService;
import com.demo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/main")
public class MainController {
    @Autowired
    private ResidentService residentService;
    @Autowired
    private RecordService recordService;
    @Autowired
    private VisitorService visitorService;
    @Autowired
    private AddressService addressService;

    /**
     * 首页 数据展示
     * 获取业主总数，地址总数，获取今日进出记录数，获取今日访客数
     */
    @PostMapping("datas")
    public Result showDatas(){
        //业主相关
        Result result = residentService.queryResCountl();
        Object data = result.getData();
        //地址相关
        long addTotal = addressService.queryAddressByParam().getTotal();
        //访客相关
        long visTotal = visitorService.queryVistorByParam().getTotal();
        //记录相关
        long recTotal = recordService.queryRecordByParam().getTotal();
        HashMap<String, Object> map = new HashMap<>(10);
        map.put("resCount",data);
        map.put("addCount",addTotal);
        map.put("visCount",visTotal);
        map.put("recCount",recTotal);

        return Result.ok(map);
    }

    /**
     * 首页  统计表
     * 记录的七天变化情况
     */
    @PostMapping("/echarts")
    public Result echarts(){
        /**
         * 获取当前时间，以及前七天的日期
         */
        String beginTime = null;
        String endTime = null;
        int size = 0;
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        HashMap<String, HashMap<String,Object>> map = new HashMap<>();
        HashMap<String, Object> dataMap = new HashMap<>();
        HashMap<String, Object> dateMap = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<String> dateList = new ArrayList<>();
        calendar.add(Calendar.DATE,-6);
        beginTime = dateFormat.format(calendar.getTime());
        calendar.add(Calendar.DATE,1);
        endTime= dateFormat.format(calendar.getTime());
        size = recordService.queryRecCountByDate(beginTime, endTime).size();
        dateList.add(beginTime);
        list.add(size);
        for (int i = 0; i < 6; i++) {
            calendar.add(Calendar.DATE,0);
            beginTime = dateFormat.format(calendar.getTime());
            calendar.add(Calendar.DATE,1);
            endTime= dateFormat.format(calendar.getTime());
            size = recordService.queryRecCountByDate(beginTime, endTime).size();
            dateList.add(beginTime);
            list.add(size);
        }
        dateMap.put("data",dateList);
        dateMap.put("type","category");
        dateMap.put("boundaryGap",false);
        dataMap.put("name","进出记录");
        dataMap.put("type","line");
        dataMap.put("data",list);
        map.put("date",dateMap);
        map.put("data",dataMap);

        return  Result.ok(map);
    }
}
