package org.csu.controller;

import org.csu.common.Code;
import org.csu.common.Result;
import org.csu.domain.Category;
import org.csu.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YinBo
 * @since 2025-03-08
 */
@RestController
@RequestMapping("/category")


public class CategoryController {
    @Autowired
    private CategoryServiceImpl categoryService;

    // 1. 新增分类
    @PostMapping
    public Result addCategory(@RequestBody Category category) {
        boolean flag = categoryService.addCategory(category);
        return new Result(flag ? Code.SAVE_OK : Code.SAVE_ERR, flag);
    }

    // 2. 查询所有分类
    @GetMapping
    public Result getCategories() {
        List<Category> categoryList = categoryService.getAllCategory();
        Integer code = categoryList != null ? Code.GET_OK : Code.GET_ERR;
        String msg = categoryList != null ? "" : "数据查询失败，请重试！";
        return new Result(code, categoryList, msg);
    }

    // 3. 更新分类
    @PutMapping()
    public Result updateCategory( @RequestBody Category category) {

        boolean flag = categoryService.updateCategory(category);
        return new Result(flag ? Code.UPDATE_OK : Code.UPDATE_ERR, flag);
    }

    // 4. 删除分类
    @DeleteMapping("/{catid}")
    public Result deleteCategory(@PathVariable String catid) {
        boolean flag = categoryService.deleteCategory(catid);
        return new Result(flag ? Code.DELETE_OK : Code.DELETE_ERR, flag);
    }


}
