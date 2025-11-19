package org.csu.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
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
@TableName("orderstatus")
public class Orderstatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("orderid")
    private Integer orderid;

    @TableField("linenum")
    private Integer linenum;

    @TableField("timestamp")
    private LocalDate timestamp;

    @TableField("status")
    private String status;
}
