package com.bizzan.bitrade.entity;

import com.bizzan.bitrade.constant.BooleanEnum;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MemberWalletForAPI {
    private Long memberId;
    private String coinId ;
    private BigDecimal balance;
    private BigDecimal frozenBalance;
    private String address;
    private BooleanEnum isLock ;
}
