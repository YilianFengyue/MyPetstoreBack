package org.csu;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.Types;
import java.util.Collections;

public class Generator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/mypetstore", "root", "1234")
                .globalConfig(builder -> {
                    builder.author("YinBo") // 设置作者
                            .outputDir("src/main/java")
                            .disableOpenDir();//禁止打开生成目录

                })
                .packageConfig(builder -> {
                    builder.parent("org.csu") // 设置父包名
                            .entity("domain") // 设置实体类包名
                            .mapper("dao") // 设置 Mapper 接口包名
                            .service("service") // 设置 Service 接口包名
                            .serviceImpl("service.impl") // 设置 Service 实现类包名
                            .xml("mappers"); // 设置 Mapper XML 文件包名

                })
                .strategyConfig(builder -> {
                    builder.addInclude("account","bannerdata","category","history","inventory"
                            ,"item","lineitem","orders","orderstatus","product","profile","sequence"
                            ,"shoppingcart","signon","supplier") // 设置需要生成的表名
                            .entityBuilder()
                            .enableLombok() // 启用 Lombok
                            .enableTableFieldAnnotation() // 启用字段注解
                            .controllerBuilder()
                            .enableRestStyle(); // 启用 REST 风格
                    builder.entityBuilder()
                            .enableFileOverride();//覆盖以生成文件
                    builder.mapperBuilder()
                            .formatMapperFileName("%sDao") // 生成 `xxDao` 形式的文件
                            .enableMapperAnnotation(); // 开启 @Mapper 注解

                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用 Freemarker 模板引擎
                .execute(); // 执行生成
    }
}
