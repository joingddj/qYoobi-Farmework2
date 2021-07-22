package com.bizzan.bitrade.controller.member;


import com.bizzan.bitrade.annotation.AccessLog;
import com.bizzan.bitrade.constant.AdminModule;
import com.bizzan.bitrade.constant.PageModel;
import com.bizzan.bitrade.controller.common.BaseAdminController;
import com.bizzan.bitrade.dto.FlowDealerDto;
import com.bizzan.bitrade.entity.Dealer;
import com.bizzan.bitrade.entity.DealerVO;
import com.bizzan.bitrade.service.DealerService;
import com.bizzan.bitrade.util.MessageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static org.springframework.util.Assert.notNull;


/**
 * @author ymy
 * @description 交易员
 * @date 2021年1月5日16:23:16
 */
@Slf4j
@RestController
@RequestMapping("/dealer")
public class DealerController extends BaseAdminController {

    @Autowired
    private DealerService dealerService;


    @PostMapping("page-query")
    @ResponseBody
    @AccessLog(module = AdminModule.MEMBER, operation = "分页查找交易员dealer")
    public MessageResult page(
            @RequestParam(value = "queryTime", required = false)Date queryTime,
            @RequestParam(value = "memberId", required = false) Long memberId,
            PageModel pageModel) {
        if (queryTime == null){
            queryTime = new Date();
        }
        List<DealerVO> all = dealerService.findDealerVOByCountDesc(queryTime,pageModel,memberId);
        return success(all);
    }

    @PostMapping("page-query-admin")
    @ResponseBody
    @AccessLog(module = AdminModule.MEMBER, operation = "分页查找交易员dealer-admin端")
    public MessageResult pageAdmin(
            PageModel pageModel) {
        Page<Dealer> page = dealerService.findAll(pageModel.getPageable());
        return success(page);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @AccessLog(module = AdminModule.MEMBER, operation = "新建交易员")
    public MessageResult add(@RequestBody Dealer dealer)  {
        notNull(dealer.getMemberId(), "validate member_id!");
        dealer.setFlowCount(0L);
        dealer.setCreateTime(new Date());
        Dealer save = dealerService.save(dealer);
        if (save == null){
            return error("system error");
        }
        return success(save.getId());
    }

    @RequestMapping(value = "flow", method = RequestMethod.POST)
    @AccessLog(module = AdminModule.MEMBER, operation = "一键跟单")
    @Transactional(rollbackFor = Exception.class)
    public MessageResult flow(@RequestBody FlowDealerDto flowDealerDto)  {
        notNull(flowDealerDto.getMemberId(), "validate member_id!");
        notNull(flowDealerDto.getDealerId(), "validate dealer_id!");
        notNull(flowDealerDto.getIfFollowed(), "validate operate!");
        MessageResult flow = dealerService.flow(flowDealerDto);
        return flow;
    }

    @RequestMapping(value = "ifFlowed", method = RequestMethod.POST)
    @AccessLog(module = AdminModule.MEMBER, operation = "跟单状态")
    public MessageResult ifFlowed(@RequestBody FlowDealerDto flowDealerDto)  {
        notNull(flowDealerDto.getMemberId(), "validate member_id!");
        notNull(flowDealerDto.getDealerId(), "validate dealer_id!");
        MessageResult flow = dealerService.ifFlowed(flowDealerDto);
        return flow;
    }


}
