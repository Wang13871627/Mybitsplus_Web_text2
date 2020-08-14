package com.huayu.mybitsplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.huayu.mybitsplus.pojo.Authority;
import com.huayu.mybitsplus.pojo.PostAuth;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AuthorityMapper extends BaseMapper<Authority> {
    @Select("select * from authority")
    public List<Authority> queryall(IPage<Authority> page);

}