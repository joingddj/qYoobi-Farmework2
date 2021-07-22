package com.bizzan.bitrade.controller;

import com.bizzan.bitrade.entity.Community;
import com.bizzan.bitrade.service.CommunityService;
import com.bizzan.bitrade.util.MessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

    @Autowired
    private CommunityService communityService;

    @GetMapping(value = "/getarticle")
    public MessageResult getarticle(@RequestParam Long id) {
        MessageResult result = new MessageResult();
        if (id != null){
            Community community = communityService.findById(id);
            result.setCode(200);
            result.setData(community);
            return result;
        }
        return MessageResult.error("查询失败,稍后重试");
    }

}
