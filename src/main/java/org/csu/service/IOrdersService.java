package org.csu.service;

import org.csu.domain.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YinBo
 * @since 2025-03-08
 */
public interface IOrdersService extends IService<Orders> {
    List<Orders> getAllOrders();
    boolean addOrder(Orders order);
    boolean deleteOrder(Integer orderId);
    boolean updateOrder(Orders order);
    Orders getOrderById(Integer orderId);
    boolean deleteOrdersByIds(List<Long> orderIds);
}
