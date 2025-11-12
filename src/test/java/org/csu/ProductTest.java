package org.csu;

import org.csu.domain.Product;
import org.csu.service.IAccountService;
import org.csu.service.IAdminService;
import org.csu.service.impl.ProductServiceImpl;
import org.csu.dao.ProductDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private IAdminService adminService;
    // 测试获取所有产品
    @Test
    void testGetAllProducts() {
        List<Product> products = productService.getAllProducts();
        System.out.println("All Products: " + products);
    }
    @Test
    void testadmin() {
      boolean a= adminService.validateUser("j2ee","123");
        System.out.println(a);
    }
    // 测试增加产品
    @Test
    void testAddProduct() {
        Product product = new Product();
        product.setProductid("1919810");
        product.setName("Product A");
        product.setCategory("12345");  // 假设存在对应的 Category
        boolean result = productService.addProduct(product);
        System.out.println("Product added: " + result);
    }

    // 测试删除产品
    @Test
    void testDeleteProduct() {
//        Product product = new Product();
//        product.setProductid("1919810");
//        boolean result = productService.deleteProduct(product);
//        System.out.println("Product deleted: " + result);
    }

    // 测试更新产品
    @Test
    void testUpdateProduct() {
        Product product = new Product();
        product.setProductid("1919810");
        product.setName("Updated Product A");
        product.setCategory("12345");  // 假设存在对应的 Category
        boolean result = productService.updateProduct(product);
        System.out.println("Product updated: " + result);
    }

    // 测试根据产品信息进行模糊查询
    @Test
    void testGetProductsByInfo() {
        List<Product> products = productService.getProductsByInfo("Product A");
        System.out.println("Products found: " + products);
    }
}
