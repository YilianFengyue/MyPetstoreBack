package org.csu.service;

import org.csu.domain.Shoppingcart;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YinBo
 * @since 2025-03-08
 */
public interface IShoppingcartService extends IService<Shoppingcart> {

    boolean addShoppingcart(Shoppingcart cart);

    boolean increaseItemQuantity(String userId,String itemId);

    boolean decreaseItemQuantity(String userId,String itemId);

    boolean removeItemFromCart(String userId,String itemId);
}
