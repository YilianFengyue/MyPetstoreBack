package org.csu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.csu.domain.Account;
import org.csu.domain.Item;
import org.csu.dao.ItemDao;
import org.csu.service.IItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YinBo
 * @since 2025-03-08
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemDao, Item> implements IItemService {
    @Autowired
    private ItemDao itemDao;


    @Override
    public List<Item> getAllItems() {
        List<Item> itemList = itemDao.selectList(null);
        return itemList;
    }

    @Override
    public boolean addItem(Item item) {
        int flag = itemDao.insert(item);
        return flag == 1;
    }

    @Override
    public boolean deleteItem(String itemid) {
        int flag = itemDao.deleteById(itemid);
        return flag==1;
    }

    @Override
    public boolean updateItem(Item item) {
        int flag=itemDao.updateById(item);
        return flag==1;
    }

    @Override
    public Item getItemById(String id) {
        Item item = itemDao.selectById(id);
        return item;
    }
    //模糊查询
    public List<Item> getItemByInfo(String info) {
        LambdaQueryWrapper<Item> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Item::getItemid, info);  // `getName` 是实体类的 getter 方法
        return itemDao.selectList(wrapper);
    }
    //上架下架商品
    @Override
    public boolean listItem(Item item) {
        return false;
    }
}
