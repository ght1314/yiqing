package com.demo.service.impl;

import com.demo.mapper.AuthorityMapper;
import com.demo.service.AuthorityService;
import com.demo.entity.AuthorityEntity;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 *  服务实现类
 */
@Service
public class AuthorityServiceImpl extends ServiceImpl<AuthorityMapper, AuthorityEntity> implements AuthorityService {

}
