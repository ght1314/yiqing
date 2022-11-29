package com.demo.controller;

import com.alibaba.excel.EasyExcel;
import com.demo.common.AddExcelListener;
import com.demo.common.ExcelListener;
import com.demo.dto.AddressDto;
import com.demo.dto.ResidentDto;
import com.demo.service.AddressService;
import com.demo.service.ResidentService;
import com.demo.utils.Result;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

@Api(value = "文件上传", tags = "文件上传相关接口")
@RestController
@RequestMapping("/api/excel")
public class ExcelController {
    public static Logger logger = LoggerFactory.getLogger(ExcelController.class);
    @Resource
    private ResidentService residentService;
    @Resource
    private AddressService addressService;

    @PostMapping("/uploadRes")
    public Result upload(@RequestParam MultipartFile file) {
        InputStream fileInputStream = null;
        try {
            fileInputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return Result.fail("上传文件失败");
        }
        try {
            EasyExcel.read(fileInputStream, ResidentDto.class, new ExcelListener(residentService)).sheet().doRead();
            return Result.ok("上传文件成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail("未知错误");
    }

    @PostMapping("/uploadAdd")
    public Result uploadd(@RequestParam MultipartFile file) {
        InputStream fileInputStream = null;
        try {
            fileInputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return Result.fail("上传文件失败");
        }
        try {
            EasyExcel.read(fileInputStream, AddressDto.class, new AddExcelListener(addressService)).sheet().doRead();
            return Result.ok("上传文件成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail("未知错误");
    }
}
