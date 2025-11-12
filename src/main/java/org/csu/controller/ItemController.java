package org.csu.controller;

import org.csu.domain.Item;
import org.csu.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YinBo
 * @since 2025-03-08
 */
@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private IItemService itemService;
    // 获取所有商品项
    @GetMapping
    public Result  getAllItems() {
        List<Item> itemList = itemService.getAllItems();
        Integer code = itemList != null ? Code.GET_OK : Code.GET_ERR;
        String msg = itemList != null ? "" : "数据查询失败，请重试！";
        return new Result(code, itemList, msg);
    }

    // 1. 新增商品项
    @PostMapping
    public Result addItem(@RequestBody Item item) {
        boolean flag = itemService.addItem(item);
        return new Result(flag ? Code.SAVE_OK : Code.SAVE_ERR, flag);
    }

    // 3. 更新商品项
    @PutMapping
    public Result updateItem( @RequestBody Item item) {
        System.out.println(item);
        boolean flag = itemService.updateItem(item);
        return new Result(flag ? Code.UPDATE_OK : Code.UPDATE_ERR, flag);
    }

    // 4. 删除商品项
    @DeleteMapping("/{itemid}")
    public Result deleteItem(@PathVariable String itemid) {
        boolean flag = itemService.deleteItem(itemid);
        return new Result(flag ? Code.DELETE_OK : Code.DELETE_ERR, flag);
    }

    // 5. 上架商品
    @PutMapping("/{itemid}/upload")
    public Result uploadItem(@PathVariable String itemid) {
        Item item = itemService.getItemById(itemid);
        if (item != null) {
            // 假设上架商品是设置某个字段为上架状态
            item.setStatus("Available"); // 示例字段
            boolean flag = itemService.updateItem(item);
            return new Result(flag ? Code.UPDATE_OK : Code.UPDATE_ERR, flag);
        }
        return new Result(Code.GET_ERR, "商品未找到");
    }
}
