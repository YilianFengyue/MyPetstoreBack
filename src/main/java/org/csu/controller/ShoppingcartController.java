package org.csu.controller;


import org.csu.domain.Shoppingcart;
import org.csu.service.IShoppingcartService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/shoppingcart")
public class ShoppingcartController {

    @Autowired
    private IShoppingcartService shoppingcartItemService;

    // 添加商品到购物车
    @PostMapping("/addition")
    public Result addItem(@RequestBody Shoppingcart cart) {
        boolean flag = shoppingcartItemService.addShoppingcart(cart);

        // 返回操作结果
        return new Result(flag ? Code.SAVE_OK : Code.SAVE_ERR, flag);
    }

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

