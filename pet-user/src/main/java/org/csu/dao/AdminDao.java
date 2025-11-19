package org.csu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.csu.domain.Admin;
@Mapper
public interface AdminDao extends BaseMapper<Admin> {
}
