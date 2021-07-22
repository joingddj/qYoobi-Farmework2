package com.bizzan.bitrade.vo;

import com.bizzan.bitrade.entity.ExchangeOrderDirection;
import com.bizzan.bitrade.entity.ExchangeOrderType;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @description: ExchangeOrderVO
 * @author: MrGao
 * @create: 2019/05/07 15:41
 */
@Data
public class ExchangeOrderVO {

    private Long memberId ;

    private ExchangeOrderDirection direction ;

    private String symbol ;

    private BigDecimal price ;

    private BigDecimal amount ;

    private ExchangeOrderType type ;


}
