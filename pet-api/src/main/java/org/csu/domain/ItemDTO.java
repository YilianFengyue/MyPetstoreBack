package org.csu.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

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

public class ItemDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    private String itemid;


    private String productid;


    private BigDecimal listprice;


    private BigDecimal unitcost;


    private Integer supplier;


    private String status;


    private String attr1;


    private String attr2;


    private String attr3;


    private String attr4;


    private String attr5;

    private Integer uploaded;
}
