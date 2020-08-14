package com.huayu.mybitsplus.interfaces;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huayu.mybitsplus.pojo.Authority;
import com.huayu.mybitsplus.pojo.PostAuth;

import java.util.List;

public interface AuthorityUtils extends IService<Authority> {
    public List<Authority> queryall(IPage<Authority> page);
}
