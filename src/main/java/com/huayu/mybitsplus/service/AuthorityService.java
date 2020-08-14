package com.huayu.mybitsplus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huayu.mybitsplus.interfaces.AuthorityUtils;
import com.huayu.mybitsplus.interfaces.PostAuthUtils;
import com.huayu.mybitsplus.mapper.AuthorityMapper;
import com.huayu.mybitsplus.mapper.PostAuthMapper;
import com.huayu.mybitsplus.pojo.Authority;
import com.huayu.mybitsplus.pojo.PostAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AuthorityService extends ServiceImpl<AuthorityMapper, Authority> implements AuthorityUtils {
    @Autowired
    private AuthorityMapper authorityMapper;

    @Override
    public List<Authority> queryall(IPage<Authority> page) {
        return authorityMapper.queryall(page);
    }
}
