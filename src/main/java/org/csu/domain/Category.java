package org.csu.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
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
@TableName("category")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("catid")
    private String catid;

    @TableField("name")
    private String name;

    @TableField("descn")
    private String descn;

    @TableField("descn1")
    private String descn1;
    @TableField("productList")
    private String productList;
}
