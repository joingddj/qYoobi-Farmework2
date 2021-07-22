package com.bizzan.bitrade.service;

import com.bizzan.bitrade.dao.ContractOptionOrderRepository;
import com.bizzan.bitrade.entity.ContractOptionOrder;
import com.bizzan.bitrade.pagination.Criteria;
import com.bizzan.bitrade.pagination.Restrictions;
import com.bizzan.bitrade.service.Base.BaseService;
import com.querydsl.core.types.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ContractOptionOrderService extends BaseService {

    @Autowired
    private LocaleMessageSourceService msService;

    @Autowired
    private ContractOptionOrderRepository contractOptionOrderRepository;

    public Page<ContractOptionOrder> findAll(Predicate predicate, Pageable pageable) {
        return contractOptionOrderRepository.findAll(predicate, pageable);
    }

    public Page<ContractOptionOrder> findAll(Long memberId, String symbol, int pageNo, int pageSize) {
        Sort orders = new Sort(new Sort.Order(Sort.Direction.DESC, "createTime"));
        PageRequest pageRequest = new PageRequest(pageNo, pageSize, orders);
        Criteria<ContractOptionOrder> specification = new Criteria<ContractOptionOrder>();
        if(symbol != null && StringUtils.isNotEmpty(symbol)) {
            specification.add(Restrictions.eq("symbol", symbol, true));
        }
        specification.add(Restrictions.eq("memberId", memberId, false));
        return contractOptionOrderRepository.findAll(specification, pageRequest);
    }

    public ContractOptionOrder save(ContractOptionOrder order) {
        return contractOptionOrderRepository.saveAndFlush(order);
    }

    public List<ContractOptionOrder> findByMemberIdAndOptionId(Long memberId, Long optionId) {
        return contractOptionOrderRepository.findByMemberIdAndOptionId(memberId, optionId);
    }

    public List<ContractOptionOrder> findByOptionId(Long optionId){
        return contractOptionOrderRepository.findByOptionId(optionId);
    }

    public List<ContractOptionOrder> findByMemberId(Long memberId) {
        return contractOptionOrderRepository.findByMemberId(memberId);
    }
}
