package org.csu.dao;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("admin") // 数据库表名
public class Admin {
    private String username;
    private String password;
}
