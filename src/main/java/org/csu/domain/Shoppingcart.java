package org.csu.domain;

import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("shoppingcart")
public class Shoppingcart implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("userid")
    private String userid;

    @TableField("itemid")
    private String itemid;

    @TableField("productid")
    private String productid;

    @TableField("description")
    private String description;

    @TableField("instock")
    private String instock;

    @TableField("quantity")
    private String quantity;

    @TableField("listprice")
    private String listprice;

    @TableField("totalcost")
    private String totalcost;

    @TableField("address")
    private String address;

    @TableField("times")
    private String times;
}
