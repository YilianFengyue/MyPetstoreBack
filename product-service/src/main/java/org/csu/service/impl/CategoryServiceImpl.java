package org.csu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.csu.dao.CategoryDao;
import org.csu.dao.ProductDao;
import org.csu.domain.Category;
import org.csu.domain.Product;
import org.csu.service.ICategoryService;
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
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, Category> implements ICategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Category> getAllCategory() {
        return categoryDao.selectList(null);
    }

    @Override
    public boolean addCategory(Category category) {
        int flag = categoryDao.insert(category);
        return flag == 1;
    }

    @Override
    public boolean deleteCategory(String catid) {
        // 删除分类时，首先检查是否有与其关联的产品（Product）
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Product::getCategory, catid);
        List<Product> products = productDao.selectList(wrapper);

        if (products.isEmpty()) {
            int flag = categoryDao.deleteById(catid);
            return flag == 1;
        } else {
            // 可以选择抛出异常或者返回失败信息
            throw new RuntimeException("Cannot delete category with associated products.");
        }
    }

    @Override
    public boolean updateCategory(Category category) {
        int flag = categoryDao.updateById(category);
        return flag == 1;
    }
    //
}
