package com.bizzan.bitrade.service.impl;

import com.bizzan.bitrade.dao.CommunityRepository;
import com.bizzan.bitrade.entity.Community;
import com.bizzan.bitrade.service.CommunityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    private CommunityRepository communityRepository;

    /***
     * 新增
     * @param community
     */
    @Override
    public void save(Community community) {
        communityRepository.save(community);
    }

    /***
     * 分页查询 并排序
     * @param pageNum
     * @return
     */
    @Override
    public Page<Community> findCommunityCriteria(Integer pageNum) {
        try {Thread.sleep(550);} catch (InterruptedException e) {e.printStackTrace();}

        if (pageNum != null && pageNum > 0){
            pageNum -= 1;
        }
        Pageable pageable = new PageRequest(pageNum, 12, Sort.Direction.DESC, "createTime");
        Page<Community> communityPage = communityRepository.findAll(pageable);
        return communityPage;
    }

    @Override
    public Community findById(Long id) {
        return communityRepository.findOne(id);
    }


}
