package com.bizzan.bitrade.dao;

import com.bizzan.bitrade.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CommunityRepository extends JpaRepository<Community, Long>, JpaSpecificationExecutor<Community> {


}
