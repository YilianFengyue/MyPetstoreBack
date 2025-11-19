package org.csu.controller;

import org.csu.client.ItemClient;
import org.csu.common.Code;
import org.csu.common.Result;
import org.csu.common.exception.BusinessException;
import org.csu.domain.Product;

import org.csu.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;


    // 1. 新增商品
    @PostMapping
    public Result addProduct(@RequestBody Product product) {
        boolean flag = productService.addProduct(product);
        return new Result(flag ? Code.SAVE_OK : Code.SAVE_ERR, flag);
    }

    // 2. 查询所有商品
    @GetMapping
    public Result getProducts() {
        List<Product> productList = productService.getAllProducts();
        Integer code = productList != null ? Code.GET_OK : Code.GET_ERR;
        String msg = productList != null ? "" : "数据查询失败，请重试！";
        return new Result(code, productList, msg);
    }

    // 3. 更新商品
    @PutMapping
    public Result updateProduct( @RequestBody Product product) {

        boolean flag = productService.updateProduct(product);
        return new Result(flag ? Code.UPDATE_OK : Code.UPDATE_ERR, flag);
    }

    // 4. 删除商品
    @DeleteMapping("/{productid}")
    public Result deleteProduct(@PathVariable String productid) {
        boolean flag = productService.deleteProduct(productid);
        if(flag == false){
            throw new BusinessException(Code.BUSINESS_ERR,"出错");
        }
        return new Result(flag ? Code.DELETE_OK : Code.DELETE_ERR, flag);
    }
    //5.上传文件
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // 定义两个上传路径
            Path uploadPath1 = Paths.get("src/main/resources/static/images/");
            Path uploadPath2 = Paths.get("C:/Users/123/Desktop/MyPetstore/SSM/src/resources/static/images/");

            // 创建目录（如果不存在）
            if (!Files.exists(uploadPath1)) {
                Files.createDirectories(uploadPath1);
            }
            if (!Files.exists(uploadPath2)) {
                Files.createDirectories(uploadPath2);
            }

            // 保存文件到两个路径
            Path path1 = uploadPath1.resolve(file.getOriginalFilename());
            Path path2 = uploadPath2.resolve(file.getOriginalFilename());

            Files.write(path1, file.getBytes());
            Files.write(path2, file.getBytes());

            return ResponseEntity.ok("文件上传成功: " + file.getOriginalFilename());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("上传失败: " + e.getMessage());
        }
    }

}
