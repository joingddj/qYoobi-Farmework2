package com.bizzan.bitrade.test;

import com.bizzan.bitrade.util.MessageResult;
import org.apache.shiro.crypto.hash.Md5Hash;

import java.util.HashMap;
import java.util.Map;

import static com.bizzan.bitrade.util.MessageResult.success;

public class MessageResultTest {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("id", "001");
        map.put("age", "002");
        map.put("im", "003");
        map.put("a", "004");
        map.put("name", "zhansgan");
        MessageResult result = new MessageResult(200, "success");
        result.setData(map);

        System.out.println(result);


        Md5Hash md5Hash = new Md5Hash("123456", "333435303631393236333635313731373132");
        System.out.println(md5Hash);

    }
}
