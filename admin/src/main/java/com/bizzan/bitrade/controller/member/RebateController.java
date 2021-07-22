package com.bizzan.bitrade.controller.member;


import com.bizzan.bitrade.annotation.AccessLog;
import com.bizzan.bitrade.constant.AdminModule;
import com.bizzan.bitrade.controller.common.BaseAdminController;
import com.bizzan.bitrade.entity.Rebate;
import com.bizzan.bitrade.service.RebateService;
import com.bizzan.bitrade.util.MessageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.Assert.notNull;


/**
 * @author ymy
 * @description 合约返利规则
 * @date 2021年1月5日16:23:16
 */
@Slf4j
@RestController
@RequestMapping("/rebate")
public class RebateController extends BaseAdminController {

    @Autowired
    private RebateService rebateService;


    @PostMapping("info")
    @ResponseBody
    @AccessLog(module = AdminModule.MEMBER, operation = "获取返利信息")
    public MessageResult info() {
        List<Rebate> all = rebateService.findAll();
        return success(all);
    }




    @RequestMapping(value = "add", method = RequestMethod.POST)
    @AccessLog(module = AdminModule.MEMBER, operation = "添加返利信息")
    public MessageResult add(@RequestBody Rebate rebate)  {
        notNull(rebate.getPercent(), "validate member_id!");
        Rebate save = rebateService.save(rebate);
        if (save == null){
            return error("添加返利信息失败!");
        }
        return success(save);
    }

}
