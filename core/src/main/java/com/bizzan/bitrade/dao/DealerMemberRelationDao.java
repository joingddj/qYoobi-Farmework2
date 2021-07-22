package com.bizzan.bitrade.dao;


import com.bizzan.bitrade.dao.base.BaseDao;
import com.bizzan.bitrade.entity.DealerMemberRelation;

import java.util.List;


public interface DealerMemberRelationDao extends BaseDao<DealerMemberRelation> {

    DealerMemberRelation findByMemberIdAndDealerId(Long memberId, Long dealerId);

    Integer deleteDealerMemberRelationByDealerIdAndMemberId(Long dealerId,Long memberId);

    List<DealerMemberRelation> findAllByDealerId(Long dealerId);
}
