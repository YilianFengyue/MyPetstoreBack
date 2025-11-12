package org.csu.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("lineitem")
public class Lineitem implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("orderid")
    private Integer orderid;

    @TableField("linenum")
    private Integer linenum;

    @TableField("itemid")
    private String itemid;

    @TableField("quantity")
    private Integer quantity;

    @TableField("unitprice")
    private BigDecimal unitprice;
}
