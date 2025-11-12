package org.csu.service;

import org.csu.domain.Product;
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
public interface IProductService extends IService<Product> {
    //0.查询所有
    List<Product> getAllProducts();
    //1.增加小类
    boolean addProduct(Product product);
    //2.删除小类
    boolean deleteProduct(String product);
    //3.修改小类
    boolean updateProduct(Product product);
    //4.模糊查询小类
    List<Product> getProductsByInfo(String info);

}
