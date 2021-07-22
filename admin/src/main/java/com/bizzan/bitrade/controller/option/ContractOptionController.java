package com.bizzan.bitrade.controller.option;

import com.bizzan.bitrade.annotation.AccessLog;
import com.bizzan.bitrade.constant.AdminModule;
import com.bizzan.bitrade.constant.PageModel;
import com.bizzan.bitrade.controller.common.BaseAdminController;
import com.bizzan.bitrade.entity.ContractOption;
import com.bizzan.bitrade.entity.ContractOptionCoin;
import com.bizzan.bitrade.entity.QContractOption;
import com.bizzan.bitrade.model.screen.ContractOptionScreen;
import com.bizzan.bitrade.service.ContractOptionService;
import com.bizzan.bitrade.util.MessageResult;
import com.bizzan.bitrade.util.PredicateUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/option")
@Slf4j
public class ContractOptionController extends BaseAdminController {
    @Autowired
    private ContractOptionService contractOptionService;

    /**
     * 查询
     * @param pageModel
     * @param screen
     * @return
     */
    @RequiresPermissions("option:page-query")
    @PostMapping("page-query")
    @AccessLog(module = AdminModule.CONTRACTOPTION, operation = "预测合约交易对 列表")
    public MessageResult detail(
            PageModel pageModel,
            ContractOptionScreen screen) {
        if (pageModel.getDirection() == null && pageModel.getProperty() == null) {
            ArrayList<Sort.Direction> directions = new ArrayList<>();
            directions.add(Sort.Direction.DESC);
            pageModel.setDirection(directions);
            List<String> property = new ArrayList<>();
            property.add("createTime");
            pageModel.setProperty(property);
        }
        //获取查询条件
        Predicate predicate = getPredicate(screen);
        Page<ContractOption> all = contractOptionService.findAll(predicate, pageModel.getPageable());
        return success(all);
    }

    private Predicate getPredicate(ContractOptionScreen screen) {
        ArrayList<BooleanExpression> booleanExpressions = new ArrayList<>();
        QContractOption qContractOption = QContractOption.contractOption;
        if (StringUtils.isNotBlank(screen.getSymbol())) {
            booleanExpressions.add(qContractOption.symbol.eq(screen.getSymbol()));
        }
        if (screen.getOptionNo() != null) {
            booleanExpressions.add(qContractOption.optionNo.eq(screen.getOptionNo()));
        }
        if (screen.getTotalBuyCount() != null) {
            booleanExpressions.add(qContractOption.totalBuyCount.gt(screen.getTotalBuyCount()));
        }
        if (screen.getTotalSellCount() != null) {
            booleanExpressions.add(qContractOption.totalSellCount.gt(screen.getTotalSellCount()));
        }
        if (screen.getTotalPl() != null) {
            booleanExpressions.add(qContractOption.totalPl.gt(screen.getTotalPl()));
        }

        return PredicateUtils.getPredicate(booleanExpressions);
    }
}
