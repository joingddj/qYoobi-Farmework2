package com.bizzan.bitrade.dao;


import com.bizzan.bitrade.dao.base.BaseDao;
import com.bizzan.bitrade.entity.Dealer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DealerDao extends BaseDao<Dealer> {

    @Query(value = "select * from dealer dea where dea.create_time < :queryTime limit :pageSize",nativeQuery = true)
    List<Dealer> queryDealerVO(@Param("queryTime")Date queryTime, @Param("pageSize")Integer pageSize);

    Dealer findDealerByMemberId(Long memberId);


}
