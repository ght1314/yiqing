package com.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.dto.UsersaltDto;
import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.utils.Result;

/**
 *  服务类
 */
public interface UsersaltService extends IService<UsersaltDto> {


    Result addSalt(int count,int role);

    IPage<UsersaltDto> querySaltByParam(Integer currentPage, Integer limit, String queryParams);

    Result deleteSalt(Integer[] saltId);

}
