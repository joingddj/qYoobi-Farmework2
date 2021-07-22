package com.bizzan.bitrade.entity;

import lombok.Data;

/**
 * 交易员VO
 *
 * @author
 * @date 2021年1月5日16:09:01
 */
@Data
public class DealerVO {


    private Long id;

    private Long memberId;

    private Long flowCount;


    private String avatar;

    private String username;

    private Boolean ifFlowed;

}
