package com.demo.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.dto.RecordDto;
import com.demo.service.RecordService;
import com.demo.utils.JsonObject;
import com.demo.utils.Result;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "进出记录接口")
@RestController
@RequestMapping("/records")
@RequiresRoles(value = {"user", "admin"}, logical = Logical.OR)
public class RecordController {
    @Autowired
    private RecordService recordService;

    @GetMapping("/getRecordByParam")
    public JsonObject getRecords(@RequestParam("page") Integer currentPage, @RequestParam("limit") Integer limit,
                                 @RequestParam(name = "params", required = false) String queryParams) {

        JsonObject<RecordDto> object = new JsonObject<>();
        if (queryParams != null) {
            JSONObject jsonObject = JSON.parseObject(queryParams);
            String keyword = jsonObject.getString("keyword");
            IPage<RecordDto> dtoIPage = recordService.queryRecordByParam(currentPage, limit, keyword);
            object.setCode(0);
            object.setMsg("ok");
            object.setCount(dtoIPage.getTotal());
            object.setData(dtoIPage);
            return object;
        } else {
            IPage<RecordDto> dtoIPage = recordService.queryRecordByParam(currentPage, limit, null);
            object.setCode(0);
            object.setMsg("ok");
            object.setCount(dtoIPage.getTotal());
            object.setData(dtoIPage);
            return object;
        }
    }


    @PostMapping("/addRecord")
    public Result addRecords(RecordDto recordDto) {
        return recordService.addRecord(recordDto);
    }

    @PostMapping("/deleteRecords")
    public Result deleteRecord(Integer[] ids) {
        return recordService.deleteRecord(ids);
    }

    @PostMapping("/updateRecords")
    public Result updateRecords(RecordDto recordDto) {
        return recordService.updateRecord(recordDto);
    }


}

