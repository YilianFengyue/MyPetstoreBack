package org.csu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.csu.dao.ShoppingcartDao;
import org.csu.domain.Shoppingcart;
import org.csu.service.IShoppingcartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YinBo
 * @since 2025-03-08
 */
@Service
public class ShoppingcartServiceImpl extends ServiceImpl<ShoppingcartDao, Shoppingcart> implements IShoppingcartService {

    @Autowired
    private ShoppingcartDao shoppingcartDao;

    @Override
    public boolean addShoppingcart(Shoppingcart cart) {
        int flag = shoppingcartDao.insert(cart);
        return flag == 1;
    }

    public boolean increaseItemQuantity(String userId, String itemId) {
        QueryWrapper<Shoppingcart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid", userId)
                .eq("itemid", itemId);
        Shoppingcart item = shoppingcartDao.selectOne(queryWrapper);
        if (item == null) return false;

        // 增加数量
        int newQty = Integer.parseInt(item.getQuantity()) + 1;
        item.setQuantity(String.valueOf(newQty));

        // 更新总价
        BigDecimal totalCost = new BigDecimal(item.getListprice()).multiply(new BigDecimal(newQty));
        item.setTotalcost(totalCost.toString());

        UpdateWrapper<Shoppingcart> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("userid", userId).eq("itemid", itemId); // 根据条件更新
        return shoppingcartDao.update(item, updateWrapper) > 0;
    }



    @Override
    public boolean decreaseItemQuantity(String userId,String itemId) {
        QueryWrapper<Shoppingcart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid", userId)
                .eq("itemid", itemId);
        Shoppingcart item = shoppingcartDao.selectOne(queryWrapper);
        if (item == null) return false;

        // 增加数量
        int newQty = Integer.parseInt(item.getQuantity()) - 1;
        item.setQuantity(String.valueOf(newQty));

        // 更新总价
        BigDecimal totalCost = new BigDecimal(item.getListprice()).multiply(new BigDecimal(newQty));
        item.setTotalcost(totalCost.toString());

        UpdateWrapper<Shoppingcart> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("userid", userId).eq("itemid", itemId); // 根据条件更新
        return shoppingcartDao.update(item, updateWrapper) > 0;
    }


    public boolean removeItemFromCart(String userId, String itemId) {
        // 创建 QueryWrapper 来设置删除条件
        QueryWrapper<Shoppingcart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid", userId) // 根据 userId 条件
                .eq("itemid", itemId); // 根据 itemId 条件

        // 执行删除操作，返回受影响的行数
        return shoppingcartDao.delete(queryWrapper) > 0; // 如果删除成功，返回 true
    }

}
