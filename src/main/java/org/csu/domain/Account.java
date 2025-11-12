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
@TableName("account")
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("userid")
    private String userid;

    @TableField("email")
    private String email;

    @TableField("firstname")
    private String firstname;

    @TableField("lastname")
    private String lastname;

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

    @TableField("country")
    private String country;

    @TableField("phone")
    private String phone;
}
