package org.csu.service.impl;

import org.csu.domain.Orders;
import org.csu.dao.OrdersDao;
import org.csu.service.IOrdersService;
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
public class OrdersServiceImpl extends ServiceImpl<OrdersDao, Orders> implements IOrdersService {
    @Autowired
    private OrdersDao ordersDao;

    @Override
    public List<Orders> getAllOrders() {
        return ordersDao.selectList(null);
    }

    @Override
    public boolean addOrder(Orders order) {
        return ordersDao.insert(order) == 1;
    }

    @Override
    public boolean deleteOrder(Integer orderId) {
        return ordersDao.deleteById(orderId) == 1;
    }

    @Override
    public boolean updateOrder(Orders order) {
        return ordersDao.updateById(order) == 1;
    }

    @Override
    public Orders getOrderById(Integer orderId) {
        return ordersDao.selectById(orderId);
    }

    @Override
    public boolean deleteOrdersByIds(List<Long> orderIds) {
        if (orderIds == null || orderIds.isEmpty()) return false;
        return ordersDao.deleteBatchIds(orderIds) > 0;
    }
}
