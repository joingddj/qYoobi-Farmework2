package com.bizzan.bitrade.service;

import com.bizzan.bitrade.dao.ContractOptionOrderRepository;
import com.bizzan.bitrade.dao.ContractOptionRepository;
import com.bizzan.bitrade.entity.ContractOption;
import com.bizzan.bitrade.entity.ContractOptionOrder;
import com.bizzan.bitrade.entity.ContractOptionStatus;
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
public class ContractOptionService extends BaseService {

    @Autowired
    private LocaleMessageSourceService msService;

    @Autowired
    private ContractOptionRepository contractOptionRepository;

    public Page<ContractOption> findAll(Predicate predicate, Pageable pageable) {
        return contractOptionRepository.findAll(predicate, pageable);
    }

    public ContractOption save(ContractOption option) {
        return contractOptionRepository.saveAndFlush(option);
    }

    public ContractOption findOne(Long optionId) {
        return contractOptionRepository.findOne(optionId);
    }

    public Page<ContractOption> findAll(String symbol, int count) {
        Sort orders = new Sort(new Sort.Order(Sort.Direction.DESC, "createTime"));
        PageRequest pageRequest = new PageRequest(0, count, orders);
        Criteria<ContractOption> specification = new Criteria<ContractOption>();
        if(symbol != null && StringUtils.isNotEmpty(symbol)) {
            specification.add(Restrictions.eq("symbol", symbol, true));
        }
        specification.add(Restrictions.eq("status", ContractOptionStatus.CLOSED, true));
        return contractOptionRepository.findAll(specification, pageRequest);
    }

    public List<ContractOption> findBySymbolAndStatus(String symbol, ContractOptionStatus status) {
        return contractOptionRepository.findBySymbolAndStatus(symbol, status);
    }
}
