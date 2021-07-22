package com.bizzan.bitrade.service;


import com.bizzan.bitrade.dao.RebateDao;
import com.bizzan.bitrade.entity.Rebate;
import com.bizzan.bitrade.service.Base.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class RebateService extends BaseService<Rebate> {

    @Autowired
    private RebateDao rebateDao;

    public Rebate save(Rebate rebate){
        return rebateDao.save(rebate);
    }

    public List<Rebate> findAll(){
        return rebateDao.findAll();
    }

}
