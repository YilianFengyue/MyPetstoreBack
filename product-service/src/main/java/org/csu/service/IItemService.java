package org.csu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.csu.domain.Item;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YinBo
 * @since 2025-03-08
 */
public interface IItemService extends IService<Item> {


    //0.查询所有
    public List<Item> getAllItems();


    //1.增加商品
    public boolean addItem(Item item);
    //2.删除商品
    public boolean deleteItem(String itemid);
    //3.修改商品
    public boolean updateItem(Item item);
    //4.查询商品
    public Item getItemById(String id);
    //4.1模糊查询商品
    public List<Item> getItemByInfo(String info);
    //5.停售与上架商品(逻辑删除）
    public boolean listItem(Item item);

}
