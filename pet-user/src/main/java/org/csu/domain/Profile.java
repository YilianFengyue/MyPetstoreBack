package org.csu.dao;

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
@TableName("profile")
public class Profile implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("userid")
    private String userid;

    @TableField("langpref")
    private String langpref;

    @TableField("favcategory")
    private String favcategory;

    @TableField("mylistopt")
    private Integer mylistopt;

    @TableField("banneropt")
    private Integer banneropt;
}
