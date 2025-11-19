package org.csu.service;

import org.csu.domain.Category;
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
public interface ICategoryService extends IService<Category> {
    //0.查询所有
    List<Category> getAllCategory();
    //1.增加大类
    boolean addCategory(Category category);
    //2.删除大类
    boolean deleteCategory(String catid);
    //3.修改大类（多表的级联问题）
    boolean updateCategory(Category category);
}
