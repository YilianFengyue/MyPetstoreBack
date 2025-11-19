package org.csu.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
/**
 * <p>
 * 
 * </p>
 *
 * @author YinBo
 * @since 2025-03-08
 */
@Getter
@Setter
@ToString
@TableName("product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("productid")
    private String productid;

    @TableField("category")
    private String category;

    @TableField("name")
    private String name;

    @TableField("descn")
    private String descn;

    @TableField("descn1")
    private String descn1;
}
