package com.bizzan.bitrade.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.bizzan.bitrade.entity.Country;

import java.util.List;

/**
 * @author Jammy
 * @date 2019年02月10日
 */
public interface CountryDao extends JpaRepository<Country,String>,JpaSpecificationExecutor<Country>,QueryDslPredicateExecutor<Country> {
    @Query("select a from Country a order by a.sort")
    List<Country> findAllOrderBySort();

    Country findByZhName(String zhname);
}
