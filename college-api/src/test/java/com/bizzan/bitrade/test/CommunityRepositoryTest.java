package com.bizzan.bitrade.test;


import com.bizzan.bitrade.dao.CommunityRepository;
import com.bizzan.bitrade.entity.Community;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CommunityRepositoryTest {

    @Autowired
    private CommunityRepository communityRepository;


    @Test
    public void test(){
        Md5Hash md5Hash = new Md5Hash("123456", "333435303631393236333635313731373132");
        System.out.println(md5Hash);

    }


}
