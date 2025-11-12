package org.csu.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
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
@TableName("item")
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("itemid")
    private String itemid;

    @TableField("productid")
    private String productid;

    @TableField("listprice")
    private BigDecimal listprice;

    @TableField("unitcost")
    private BigDecimal unitcost;

    @TableField("supplier")
    private Integer supplier;

    @TableField("status")
    private String status;

    @TableField("attr1")
    private String attr1;

    @TableField("attr2")
    private String attr2;

    @TableField("attr3")
    private String attr3;

    @TableField("attr4")
    private String attr4;

    @TableField("attr5")
    private String attr5;

    //上架下架
//    @TableLogic(value="0",delval="1")
    //value为正常数据的值，delval为删除数据的值
    private Integer uploaded;
}
