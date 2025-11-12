package org.csu;


import org.csu.dao.*;


import org.csu.domain.Account;
import org.csu.domain.Item;
import org.csu.service.IOrdersService;
import org.csu.service.impl.ItemServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ItemTest {
    @Autowired
    private ItemServiceImpl itemService;
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private IOrdersService ordersService;
    @Test
    void TestUpDate(){
        Item item = new Item();
        item.setItemid("EST-1");
        item.setAttr2("114514");
        itemService.updateItem(item);
    }
    @Test
    void TestDelete(){
        //删除指定多条数据
        List list = new ArrayList<>();
        list.add(1024);
        list.add(1025);

        ordersService.deleteOrdersByIds(list);
    }
    @Test
    void TestFindAll(){
        List<Item> items=   itemService.getAllItems();
        System.out.println(items);
    }
    @Test
    //注意外键约束
    void saveItem(){
        Item item = new Item();
        item.setItemid("EST-114514");
        item.setProductid("1919810");
        item.setAttr2("114514");
        itemService.addItem(item);

    }
    @Test
    void findItem(){
        Item item1=   itemService.getItemById("EST-1");
        System.out.println("item1="+item1);
    }

    @Test
    void searchItem(){
        List<Item> items=itemService.getItemByInfo("EST");
        System.out.println(items);
    }
    //逻辑删除上下架
    @Test
    void uploadItem(){
        itemDao.deleteById("EST-1112");
    }

}
