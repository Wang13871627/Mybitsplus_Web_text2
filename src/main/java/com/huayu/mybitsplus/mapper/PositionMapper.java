package com.huayu.mybitsplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.huayu.mybitsplus.pojo.Position;

import java.util.List;

public interface PositionMapper extends BaseMapper<Position> {
    List<Position> queryone1(String s);

    List<Position> queryall(IPage<Position> page);
}