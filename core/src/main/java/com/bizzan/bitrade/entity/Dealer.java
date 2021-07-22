package com.bizzan.bitrade.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 交易员
 *
 * @author
 * @date 2021年1月5日16:09:01
 */
@Data
@Entity
public class Dealer {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Long memberId;

    private Long flowCount;

    private Date createTime;

}
