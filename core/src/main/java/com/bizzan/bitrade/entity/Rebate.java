package com.bizzan.bitrade.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 合约利率规则
 *
 * @author ic3
 * @date 2021年1月5日16:09:01
 */
@Data
@Entity
public class Rebate {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private BigDecimal percent;
}
