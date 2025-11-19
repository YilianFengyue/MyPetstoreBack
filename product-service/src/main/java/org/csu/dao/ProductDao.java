package org.csu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.csu.domain.Product;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YinBo
 * @since 2025-03-08
 */
@Mapper
public interface ProductDao extends BaseMapper<Product> {

}

