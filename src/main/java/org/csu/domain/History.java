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
@TableName("history")
public class History implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("userid")
    private String userid;

    @TableField("category")
    private String category;

    @TableField("addtocarttimes")
    private Integer addtocarttimes;

    @TableField("visittimes")
    private Integer visittimes;
}
