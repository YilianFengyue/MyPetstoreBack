package org.csu.dao;

import org.csu.domain.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YinBo
 * @since 2025-03-08
 */
@Mapper
public interface OrdersDao extends BaseMapper<Orders> {

}

