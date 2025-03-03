package org.example.brightseed.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.brightseed.model.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}