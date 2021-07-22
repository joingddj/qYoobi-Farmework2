package com.bizzan.bitrade.component;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.io.InputStream;
import java.util.UUID;

@Slf4j
public class OssTemplate {

    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.oss.bucketName}")
    private String bucketName;

    public String upload(InputStream inputStream, String filename) {
        try {
            filename = UUID.randomUUID().toString().replaceAll("-", "") + "_" + filename;
            //创建ossclient实例
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            // 上传文件流。
            ossClient.putObject(bucketName, "opt/" + filename, inputStream);
            ossClient.shutdown();
            String filepath = "https://" + bucketName + "." + endpoint + "/opt/" + filename;
            log.debug("filepath{}, ", filepath);
            return filepath;
        } catch (OSSException e) {
            e.printStackTrace();
            return null;
        } catch (ClientException e) {
            e.printStackTrace();
            return null;
        }

    }


}
