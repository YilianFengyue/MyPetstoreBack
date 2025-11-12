package org.csu.controller;

import org.csu.domain.Orders;
import org.csu.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private IOrdersService ordersService;

    @GetMapping
    public Result getAllOrders() {
        List<Orders> ordersList = ordersService.getAllOrders();
        return new Result(ordersList != null ? Code.GET_OK : Code.GET_ERR, ordersList);
    }

    @PostMapping
    public Result addOrder(@RequestBody Orders order) {
        boolean flag = ordersService.addOrder(order);
        return new Result(flag ? Code.SAVE_OK : Code.SAVE_ERR, flag);
    }

    @PutMapping
    public Result updateOrder(@RequestBody Orders order) {
        boolean flag = ordersService.updateOrder(order);
        return new Result(flag ? Code.UPDATE_OK : Code.UPDATE_ERR, flag);
    }

    @DeleteMapping("/{orderId}")
    public Result deleteOrder(@PathVariable Integer orderId) {
        boolean flag = ordersService.deleteOrder(orderId);
        return new Result(flag ? Code.DELETE_OK : Code.DELETE_ERR, flag);
    }

    @GetMapping("/{orderId}")
    public Result getOrderById(@PathVariable Integer orderId) {
        Orders order = ordersService.getOrderById(orderId);
        return new Result(order != null ? Code.GET_OK : Code.GET_ERR, order);
    }
    // 批量删除
    @PostMapping("/delete")
    public ResponseEntity<?> batchDeleteOrders(@RequestBody Map<String, List<Long>> request) {
        List<Long> orderIds = request.get("orderIds");
        if (orderIds == null || orderIds.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "订单 ID 不能为空"));
        }

        boolean success = ordersService.deleteOrdersByIds(orderIds);
        return ResponseEntity.ok(Map.of("message", success ? "删除成功" : "删除失败"));
    }
}
