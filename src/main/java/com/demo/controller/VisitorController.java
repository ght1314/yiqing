package com.demo.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.dto.VistorDto;
import com.demo.service.VisitorService;
import com.demo.utils.JsonObject;
import com.demo.utils.Result;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 来访客人登记表 前端控制器
 */
@RestController
@RequestMapping("/visitors")
@RequiresRoles(value={"user","admin"},logical= Logical.OR)
public class VisitorController {

    @Autowired
    private VisitorService visitorService;

    @GetMapping("/queryVisByParam")
    public JsonObject queryVisByParam(@RequestParam("page")Integer currentPage, @RequestParam("limit")Integer limit,
                                      @RequestParam(name = "params",required = false) String  queryParams)
    {
        JsonObject<VistorDto> object = new JsonObject<>();
        IPage<VistorDto> dtoIPage = visitorService.queryVistorByParam(currentPage,limit,queryParams);
        object.setCode(0);
        object.setMsg("ok");
        object.setCount(dtoIPage.getTotal());
        object.setData(dtoIPage);
        return object;
    }

    @PostMapping("/addVisitor")
    public Result addVistor(VistorDto vistorDto)
    {
        return visitorService.addVistor(vistorDto);
    }

    @PostMapping("/deleteVisitors")
    public Result deleteVistors(Integer[] ids){
        return visitorService.deleteVistors(ids);
    }

    @PostMapping("/updateVisitor")
    public Result updateVistor(VistorDto vistorDto){
        return visitorService.updateVistor(vistorDto);
    }


}

