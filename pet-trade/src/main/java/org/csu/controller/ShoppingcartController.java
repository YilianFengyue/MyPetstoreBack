package org.csu.controller;


import org.csu.client.ItemClient;
import org.csu.common.Code;
import org.csu.common.Result;
import org.csu.common.utils.ThreadLocalUtil;
import org.csu.domain.Shoppingcart;
import org.csu.service.IShoppingcartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YinBo
 * @since 2025-03-08
 */
@RestController
@RequestMapping("/shoppingcart")
@RequiredArgsConstructor
public class ShoppingcartController {

    @Autowired
    private IShoppingcartService shoppingcartItemService;

    @Autowired
    private ItemClient itemClient;
    // ⭐ 新增：通过 Feign 查询商品详情
    @GetMapping("/item/{id}")
    public Result getItemDetail(@PathVariable String id) {
        // 远程调用商品服务
        return itemClient.getItemById(id);
    }

    // ⭐ 新增：添加商品到购物车前先验证商品是否存在

    @PostMapping("/addition")
    public Result addItem(@RequestBody Shoppingcart cart) {
        // 1. 先通过 Feign 调用商品服务验证商品是否存在
        Result itemResult = itemClient.getItemById(cart.getItemid());
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        cart.setUserid(username);
        if (itemResult == null || !itemResult.getCode().equals(Code.GET_OK)) {
            return new Result(Code.SAVE_ERR, "商品不存在或已下架");
        }

        // 2. 验证通过，添加到购物车
        boolean flag = shoppingcartItemService.addShoppingcart(cart);
        return new Result(flag ? Code.SAVE_OK : Code.SAVE_ERR, flag);
    }

//    // 添加商品到购物车
//    @PostMapping("/addition")
//    public Result addItem(@RequestBody Shoppingcart cart) {
//        boolean flag = shoppingcartItemService.addShoppingcart(cart);
//
//        // 返回操作结果
//        return new Result(flag ? Code.SAVE_OK : Code.SAVE_ERR, flag);
//    }

    // 增加商品数量
    @PostMapping("/increase/{userid}/{itemId}")
    public Result increaseItemQuantity(@PathVariable String userid,@PathVariable String itemId) {
        boolean flag = shoppingcartItemService.increaseItemQuantity(userid,itemId);
        return new Result(flag ? Code.UPDATE_OK : Code.UPDATE_ERR, flag);
    }

    // 减少商品数量
    @PostMapping("/decrease/{userid}/{itemId}")
    public Result decreaseItemQuantity(@PathVariable String userid,@PathVariable String itemId) {
        boolean flag = shoppingcartItemService.decreaseItemQuantity(userid,itemId);
        return new Result(flag ? Code.UPDATE_OK : Code.UPDATE_ERR, flag);
    }

    // 删除商品
    @DeleteMapping("/removal/{userid}/{itemId}")
    public Result removeItem(@PathVariable String userid,@PathVariable String itemId) {
        boolean flag = shoppingcartItemService.removeItemFromCart(userid,itemId);
        return new Result(flag ? Code.DELETE_OK : Code.DELETE_ERR, flag);
    }


}

