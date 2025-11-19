package org.csu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.csu.dao.ItemDao;
import org.csu.dao.ProductDao;
import org.csu.domain.Item;
import org.csu.domain.Product;
import org.csu.service.IProductService;
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
public class ProductServiceImpl extends ServiceImpl<ProductDao, Product> implements IProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ItemDao itemDao; // 引用 itemDao，用于处理级联操作

    @Override
    public List<Product> getAllProducts() {
        return productDao.selectList(null);
    }

    @Override
    public boolean addProduct(Product product) {
        int flag = productDao.insert(product);
        return flag == 1;
    }

    @Override
    public boolean deleteProduct(String productid) {
        // 删除产品时，要确保没有与其关联的商品（Item）存在
        LambdaQueryWrapper<Item> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Item::getProductid,productid);
        List<Item> items = itemDao.selectList(wrapper);

        if (items.isEmpty()) {
            int flag = productDao.deleteById(productid);
            return flag == 1;
        } else {
            // 可以选择直接抛出异常或返回失败信息
            throw new RuntimeException("Cannot delete product with associated items.");
        }
    }

    @Override
    public boolean updateProduct(Product product) {
        int flag = productDao.updateById(product);
        return flag == 1;
    }

    @Override
    public List<Product> getProductsByInfo(String info) {
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Product::getName, info);  // 以商品名称为条件进行模糊查询
        return productDao.selectList(wrapper);
    }
}
