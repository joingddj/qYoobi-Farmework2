package com.bizzan.bitrade.service;

import com.bizzan.bitrade.entity.Community;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommunityService {

    void save(Community community);

    Page<Community> findCommunityCriteria(Integer pageNum);

    Community findById(Long id);
}
