package com.demo.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.dto.AddressDto;
import com.demo.service.AddressService;
import com.demo.utils.JsonObject;
import com.demo.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "地址相关接口")
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/queryAddressByParam")
    public JsonObject queryAddressByParam(@RequestParam("page") Integer currentPage, @RequestParam("limit") Integer limit,
                                          @RequestParam(name = "params", required = false) String queryParams) {
        JsonObject<AddressDto> object = new JsonObject<>();
        if (queryParams != null) {
            JSONObject jsonObject = JSON.parseObject(queryParams);
            String keyword = jsonObject.getString("keyword");
            IPage<AddressDto> dtoIPage = addressService.queryAddressByParam(currentPage, limit, keyword);
            object.setCode(0);
            object.setMsg("ok");
            object.setCount(dtoIPage.getTotal());
            object.setData(dtoIPage);
            return object;
        } else {
            IPage<AddressDto> dtoIPage = addressService.queryAddressByParam(currentPage, limit, queryParams);
            object.setCode(0);
            object.setMsg("ok");
            object.setCount(dtoIPage.getTotal());
            object.setData(dtoIPage);
            return object;
        }
    }

    @PostMapping("/addAddress")
    public Result addAddress(AddressDto addressDto) {
        return addressService.addAddress(addressDto);
    }

    @PostMapping("/deleteAddress")
    public Result deleteAddress(Integer[] ids) {
        return addressService.deleteAddress(ids);
    }

    @PostMapping("/updateAddress")
    public Result updateAddress(AddressDto addressDto) {
        return addressService.updateAddress(addressDto);
    }

}

