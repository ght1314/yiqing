package com.demo.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.dto.ResidentDto;
import com.demo.service.ResidentService;
import com.demo.utils.JsonObject;
import com.demo.utils.Result;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/residents")
@RequiresRoles(value={"user","admin"},logical= Logical.OR)
@Api(value = "业主管理相关接口",tags ="业主管理相关接口" )
public class ResidentController {

    @Autowired
    private ResidentService residentService;

    /**
     * 查找登记信息
     * @param currentPage 当前页
     * @param limit  页面大小
     * @param queryParams 查询条件，可以为null
     * @return
     */
    @GetMapping("/residentsByParams")
    public JsonObject residentList(@RequestParam("page")Integer currentPage, @RequestParam("limit")Integer limit,
                                   @RequestParam(name = "params",required = false) String  queryParams)
    {
        JsonObject<ResidentDto> object = new JsonObject<>();
        if (queryParams == null){
            IPage<ResidentDto> dtoIPage = residentService.residentsByParams(currentPage,limit,null,null,null);
            object.setCode(0);
            object.setMsg("ok");
            object.setCount(dtoIPage.getTotal());
            object.setData(dtoIPage);
            return object;
        }else {
            JSONObject jsonObject=JSON.parseObject(queryParams);
            String keyword = jsonObject.getString("keyword");
            String addHouse = jsonObject.getString("addHouse");
            String addUnit = jsonObject.getString("addUnit");
            IPage<ResidentDto> dtoIPage = residentService.residentsByParams(currentPage,limit,keyword,addHouse,addUnit);
            object.setCode(0);
            object.setMsg("ok");
            object.setCount(dtoIPage.getTotal());
            object.setData(dtoIPage);
            return object;
        }

    }


    /**
     * @param residentDto
     * @return
     * 新增登记信息
     */
    @PostMapping("/addResSubmit")
    public Result addResSubmit(ResidentDto residentDto)
    {
         return residentService.addResident(residentDto);
    }

    /**
     * 更新登记信息
     * @param residentDto
     * @return
     */
    @PostMapping("/updateResSubmit")
    public Result updateResSubmit(ResidentDto residentDto)
    {
        return residentService.updateResident(residentDto);

    }

    /**
     * 删除登记信息
     * @param ids
     * @return
     */
    @PostMapping("/delResident")
    public Result delResSubmit(Integer[] ids)
    {
       return residentService.deleteResidents(ids);

}
    /**
     * 高危业主搜索
     */
    @GetMapping("/residentPeril")
    public JsonObject residentPeril(@RequestParam("page")Integer currentPage, @RequestParam("limit")Integer limit){
        JsonObject<ResidentDto> object = new JsonObject<>();
            IPage<ResidentDto> dtoIPage = residentService.queryResPeril(currentPage,limit);
            object.setCode(0);
            object.setMsg("ok");
            object.setCount(dtoIPage.getTotal());
            object.setData(dtoIPage);
            return object;
    }

    /**
     * 业主数量统计
     * @return
     */
    @PostMapping("/resEcharts")
    public Result echarts(){
        return residentService.queryResCountByDate();
    }

}

