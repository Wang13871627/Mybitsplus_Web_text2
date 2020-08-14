package com.huayu.mybitsplus.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huayu.mybitsplus.interfaces.DeptUtils;
import com.huayu.mybitsplus.interfaces.PostAuthUtils;
import com.huayu.mybitsplus.mapper.DeptMapper;
import com.huayu.mybitsplus.mapper.PostAuthMapper;
import com.huayu.mybitsplus.pojo.DeptMenu;
import com.huayu.mybitsplus.pojo.PostAuth;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PostAuthService extends ServiceImpl<PostAuthMapper, PostAuth> implements PostAuthUtils {

}
