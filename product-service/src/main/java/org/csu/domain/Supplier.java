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
@TableName("supplier")
public class Supplier implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("suppid")
    private Integer suppid;

    @TableField("name")
    private String name;

    @TableField("status")
    private String status;

    @TableField("addr1")
    private String addr1;

    @TableField("addr2")
    private String addr2;

    @TableField("city")
    private String city;

    @TableField("state")
    private String state;

    @TableField("zip")
    private String zip;

    @TableField("phone")
    private String phone;
}
