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
@TableName("bannerdata")
public class Bannerdata implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("favcategory")
    private String favcategory;

    @TableField("bannername")
    private String bannername;
}
