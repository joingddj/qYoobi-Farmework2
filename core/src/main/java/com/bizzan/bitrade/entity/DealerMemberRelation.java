package com.bizzan.bitrade.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 交易员与会员关系表
 *
 * @author 
 * @date 2021年1月5日16:09:01
 */
@Data
@Entity
public class DealerMemberRelation {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Long dealerId;

    private Long memberId;

}
