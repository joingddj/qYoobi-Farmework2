package com.bizzan.bitrade.dto;


import lombok.Data;

@Data
public class FlowDealerDto {

    private Long dealerId;

    private Long memberId;
    //是否跟随 0 取消跟随  1 跟随
    private Boolean ifFollowed;
}
