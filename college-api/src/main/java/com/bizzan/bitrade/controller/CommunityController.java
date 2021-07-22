package com.bizzan.bitrade.controller;


import com.bizzan.bitrade.component.OssTemplate;
import com.bizzan.bitrade.entity.Community;
import com.bizzan.bitrade.service.CommunityService;
import com.bizzan.bitrade.service.LocaleMessageSourceService;
import com.bizzan.bitrade.util.MessageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Slf4j
@CrossOrigin
public class CommunityController {


    @Autowired
    private OssTemplate ossTemplate;

    @Autowired
    private CommunityService communityService;


    @Autowired
    private LocaleMessageSourceService msService;

    @PostMapping(value = "/addimage")
    public MessageResult addimage(String content, String title, @RequestPart("file") MultipartFile file) {
        log.info("上传的信息: content{}, title{}, file{}", content, content, file.getSize());
        if (!file.isEmpty() && !content.isEmpty() && !title.isEmpty()) {
            try {
                String originalFilename = file.getOriginalFilename();
                String filepath = ossTemplate.upload(file.getInputStream(), originalFilename);
                Community community = new Community();
                community.setTitle(title);
                community.setContent(content);
                community.setImgurl(filepath);
                communityService.save(community);
                return MessageResult.success("上传成功");
            } catch (IOException e) {
                e.printStackTrace();
                return MessageResult.error(500, msService.getMessage("上传出现错误,请稍后重试"));
            }
        }
        return MessageResult.error(500, msService.getMessage("上传的数据不能为空"));
    }


    @GetMapping("/getimage")
    public MessageResult gitImage(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum) {
        MessageResult result = new MessageResult();
        Page<Community> communityCriteria = communityService.findCommunityCriteria(pageNum);
        if (communityCriteria != null && communityCriteria.getSize() > 0) {
            result.setCode(200);
            result.setMessage("SUCCESS");
            result.setData(communityCriteria);
            return result;
        }
        return MessageResult.error(500, msService.getMessage("查询失败，请稍后重试"));



    }


}
