package org.csu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.csu.domain.Item;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YinBo
 * @since 2025-03-08
 */
@Mapper
public interface ItemDao extends BaseMapper<Item> {

        @Update("UPDATE item SET status = #{status} WHERE itemid = #{itemid} AND deleted = 0")
        int updateItemStatus(String itemid, int status);

}

