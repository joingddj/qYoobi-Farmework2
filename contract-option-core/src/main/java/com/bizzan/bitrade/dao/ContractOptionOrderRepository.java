package com.bizzan.bitrade.dao;

import com.bizzan.bitrade.entity.ContractOptionOrder;
import com.bizzan.bitrade.entity.ContractOptionOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContractOptionOrderRepository extends JpaRepository<ContractOptionOrder, String>, JpaSpecificationExecutor<ContractOptionOrder>, QueryDslPredicateExecutor<ContractOptionOrder> {
    @Modifying
    @Query("update ContractOptionOrder contract set contract.status = :status where contract.id = :contractOptionOrderId")
    int updateStatus(@Param("contractOptionOrderId") String contractOptionOrderId, @Param("status") ContractOptionOrderStatus status);

    List<ContractOptionOrder> findByMemberIdAndOptionId(Long memberId, Long optionId);

    List<ContractOptionOrder> findByOptionId(Long optionId);

    List<ContractOptionOrder> findByMemberId(Long memberId);
}
