package org.csu;

import org.csu.domain.Category;
import org.csu.service.impl.CategoryServiceImpl;
import org.csu.dao.CategoryDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;
    @Autowired
    private CategoryDao categoryDao;

    // 测试获取所有分类
    @Test
    void testGetAllCategory() {
        List<Category> categories = categoryService.getAllCategory();
        System.out.println("All Categories: " + categories);
    }

    // 测试新增分类
    @Test
    void testAddCategory() {
        Category category = new Category();
        category.setCatid("123455");
        category.setName("Category A");
        boolean result = categoryService.addCategory(category);
        System.out.println("Category added: " + result);
    }

    // 测试删除分类
    @Test
    void testDeleteCategory() {
        Category category = new Category();
        category.setCatid("12345");  // 确保该分类存在
        boolean result = categoryService.deleteCategory(category.getCatid());
        System.out.println("Category deleted: " + result);
    }

    // 测试更新分类
    @Test
    void testUpdateCategory() {
        Category category = new Category();
        category.setCatid("12345");
        category.setName("Updated Category A");
        boolean result = categoryService.updateCategory(category);
        System.out.println("Category updated: " + result);
    }

    // 测试根据分类信息进行模糊查询
    @Test
    void testGetCategoryByInfo() {
        List<Category> categories = categoryService.getAllCategory();  // 假设获取所有分类并查询
        System.out.println("Categories found: " + categories);
    }
}
