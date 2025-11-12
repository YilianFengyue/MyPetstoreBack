package org.csu.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

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
@TableName("orders")
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("orderid")
    private Integer orderid;

    @TableField("userid")
    private String userid;

    @TableField("orderdate")
    private Date orderdate;

    @TableField("shipaddr1")
    private String shipaddr1;

    @TableField("shipaddr2")
    private String shipaddr2;

    @TableField("shipcity")
    private String shipcity;

    @TableField("shipstate")
    private String shipstate;

    @TableField("shipzip")
    private String shipzip;

    @TableField("shipcountry")
    private String shipcountry;

    @TableField("billaddr1")
    private String billaddr1;

    @TableField("billaddr2")
    private String billaddr2;

    @TableField("billcity")
    private String billcity;

    @TableField("billstate")
    private String billstate;

    @TableField("billzip")
    private String billzip;

    @TableField("billcountry")
    private String billcountry;

    @TableField("courier")
    private String courier;

    @TableField("totalprice")
    private BigDecimal totalprice;

    @TableField("billtofirstname")
    private String billtofirstname;

    @TableField("billtolastname")
    private String billtolastname;

    @TableField("shiptofirstname")
    private String shiptofirstname;

    @TableField("shiptolastname")
    private String shiptolastname;

    @TableField("creditcard")
    private String creditcard;

    @TableField("exprdate")
    private String exprdate;

    @TableField("cardtype")
    private String cardtype;

    @TableField("locale")
    private String locale;
}
