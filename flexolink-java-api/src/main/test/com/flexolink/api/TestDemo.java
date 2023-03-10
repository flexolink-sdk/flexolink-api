package com.flexolink.api;


import com.flexolink.api.feign.FlexolinkFeignClient;
import com.flexolink.api.param.AccessTokenParam;
import com.flexolink.api.util.SignUtil;
import com.flexolink.api.vo.AccessTokenVo;
import com.flexolink.api.vo.Message;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDemo {
    @Autowired
    FlexolinkFeignClient flexolinkFeignClient;
    /**
     * 开发者appKey
     */
    private final static String APP_KEY = "rl51038ba08d20";
    /**
     * 开发者appSecretz
     */
    private final static String APP_SECRET = "fbf6387fcbbd4c6e80bc6c02cc530d76";

    /**
     * 获取token
     *
     * @return
     */
    private AccessTokenVo getToken() {
        AccessTokenParam accessTokenParam = new AccessTokenParam();
        // 开发者appKey
        accessTokenParam.setAppKey(APP_KEY);
        // 开发者appSecret
        accessTokenParam.setAppSecret(APP_SECRET);
        Map<String, Object> body = SignUtil.toMap(accessTokenParam);
        body.put("sign", SignUtil.sign(body));
        log.info("body:{}", body.toString());
        Message<AccessTokenVo> message = flexolinkFeignClient.getToken(body);
        if (200 == message.getCode()) {
            //请求成功
            log.info("success:{}", message.toString());
        } else {
            //请求失败
            log.error("error:{}", message.toString());
        }
        return message.getData();
    }

    /**
     * 授权
     */
    @Test
    public void testToken() {
        // 参考文档地址:https://openplatform.flexolinkai.com/#/guide/api/catalog/authorization
        AccessTokenVo accessTokenVo = getToken();
        log.info("accessTokenVo:{}", accessTokenVo);
    }

    /**
     * 绑定设备
     */
    @Test
    public void testAddDevice() {
        // 参考文档地址:https://openplatform.flexolinkai.com/#/guide/api/catalog/bind
        AccessTokenVo accessTokenVo = getToken();
        Map<String, Object> body = new HashMap<>();
        // 开发者自定义名称（必传）
        body.put("deviceName", "设备名称");
        // 设备编号（蓝牙名称）（必传）
        body.put("deviceNumber", "Flex-BM05-500025");
        // 报告完成时回调地址（非必传）
        body.put("callbackUrl", "http://demo.com/");
        body.put("sign", SignUtil.sign(body));
        log.info("body:{}", body.toString());
        Message<Map<String, Object>> message = flexolinkFeignClient.addDevice(accessTokenVo.getAccessToken(), body);
        if (200 == message.getCode()) {
            //请求成功
            log.info("success:{}", message.toString());
        } else {
            //请求失败
            log.error("error:{}", message.toString());
        }
    }

    /**
     * 获取开发者设备列表
     */
    @Test
    public void testShopList() {
        // 参考文档地址:https://openplatform.flexolinkai.com/#/guide/api/catalog/list
        AccessTokenVo accessTokenVo = getToken();
        Map<String, Object> body = new HashMap<>();
        // 页码
        body.put("page", 1);
        // 每页数量 默认20
        body.put("size", 20);
        // 设备名称（非必传）
        //  body.put("deviceName", "设备名称");
        // 设备编号（非必传）
        // body.put("deviceNumber", "设备编号");
        body.put("sign", SignUtil.sign(body));
        log.info("body:{}", body.toString());
        Message<Map<String, Object>> message = flexolinkFeignClient.getShopList(accessTokenVo.getAccessToken(), body);
        if (200 == message.getCode()) {
            //请求成功
            log.info("success:{}", message.toString());
        } else {
            //请求失败
            log.error("error:{}", message.toString());
        }
    }

    /**
     * 查询开发者指定设备
     */
    @Test
    public void testGetDevice() {
        // 参考文档地址:https://openplatform.flexolinkai.com/#/guide/api/catalog/query
        AccessTokenVo accessTokenVo = getToken();
        // 开发者设备id（必传）开发者设备列表获取
        Integer id = 645;
        Map<String, Object> body = new HashMap<>();
        body.put("id", id);
        body.put("sign", SignUtil.sign(body));
        log.info("body:{}", body.toString());
        Message<Map<String, Object>> message = flexolinkFeignClient.getDevice(accessTokenVo.getAccessToken(), body);
        if (200 == message.getCode()) {
            //请求成功
            log.info("success:{}", message.toString());
        } else {
            //请求失败
            log.error("error:{}", message.toString());
        }
    }


    /**
     * 修改设备
     */
    @Test
    public void testSetDevice() {
        // 参考文档地址:https://openplatform.flexolinkai.com/#/guide/api/catalog/edit
        AccessTokenVo accessTokenVo = getToken();
        Map<String, Object> body = new HashMap<>();
        // 开发者设备id（必传）开发者设备列表获取
        body.put("id", 644);
        // 开发者设备名称（非必传）
        body.put("deviceName", "设备名称");
        // 报告完成时回调地址（非必传）
        body.put("callbackUrl", "报告回调地址(使用该设备产生的睡眠报告，生成会自动请求客户的接口将信息输出)");
        body.put("sign", SignUtil.sign(body));
        log.info("map:{}", body.toString());
        Message<Boolean> message = flexolinkFeignClient.setDevice(accessTokenVo.getAccessToken(), body);
        if (200 == message.getCode()) {
            //请求成功
            log.info("success:{}", message.toString());
        } else {
            //请求失败
            log.error("error:{}", message.toString());
        }
    }

    /**
     * 解绑设备
     */
    @Test
    public void testDelDevice() {
        // 参考文档地址:https://openplatform.flexolinkai.com/#/guide/api/catalog/unbind
        AccessTokenVo accessTokenVo = getToken();
        Integer id = 644;
        Map<String, Object> body = new HashMap<>();
        // 开发者设备id（必传）开发者设备列表获取
        body.put("id", id);
        body.put("sign", SignUtil.sign(body));
        log.info("map:{}", body.toString());
        Message<Boolean> message = flexolinkFeignClient.delDevice(accessTokenVo.getAccessToken(), body);
        if (200 == message.getCode()) {
            //请求成功
            log.info("success:{}", message.toString());
        } else {
            //请求失败
            log.error("error:{}", message.toString());
        }
    }


    /**
     * 获取开发者睡眠报告明细
     */
    @Test
    public void testGetSleepReport() {
        // 参考文档地址:https://openplatform.flexolinkai.com/#/guide/api/catalog/details
        AccessTokenVo accessTokenVo = getToken();
        Map<String, Object> body = new HashMap<>();
        // 开发者报告id（必传）（从开发者报告列表获取，生成报告时也会返回）
        body.put("id", 55159);
        body.put("sign", SignUtil.sign(body));
        log.info("map:{}", body.toString());
        Message<Map<String, Object>> message = flexolinkFeignClient.getPlatformSleepReport(accessTokenVo.getAccessToken(), body);
        if (200 == message.getCode()) {
            //请求成功
            log.info("success:{}", message.toString());
        } else {
            //请求失败
            log.error("error:{}", message.toString());
        }
    }


    /**
     * 开发者睡眠报告列表
     */
    @Test
    public void testGetSleepReportList() {
        AccessTokenVo accessTokenVo = getToken();
        Map<String, Object> body = new HashMap<>();
        // 设备编（非必传）
        body.put("deviceNumber", "设备编号");
        // edfUrl网络地址（非必传）
        body.put("edfUrl", "edf");
        // 睡眠报告类型  CONSUME_SLEEP_REPORT 消费级睡眠报告 HOSPITAL_SLEEP_REPORT 医疗级睡眠报告
        body.put("sleepReportType", "CONSUME_SLEEP_REPORT");
        body.put("page", 1);
        body.put("size", 20);
        body.put("sign", SignUtil.sign(body));
        log.info("body:{}", body.toString());
        Message<Map<String, Object>> message = flexolinkFeignClient.getSleepReportList(accessTokenVo.getAccessToken(), body);
        if (200 == message.getCode()) {
            //请求成功
            log.info("success:{}", message.toString());
        } else {
            //请求失败
            log.error("error:{}", message.toString());
        }
    }

    /**
     * 生成睡眠报告
     */
    @Test
    public void testGenerateSleepReport() {
        // 参考文档地址:https://openplatform.flexolinkai.com/#/guide/api/catalog/generateReport
        AccessTokenVo accessTokenVo = getToken();
        Map<String, Object> body = new HashMap<>();
        // 设备编（必传）
        body.put("deviceNumber", "Flex-BM05-500025");
        // edfUrl网络地址（必传）
        body.put("edfUrl", "https://rlkj-test.oss-cn-shenzhen.aliyuncs.com/2023-01-11-1110-林沛伟-Flex-BM05-060009.edf");
        // 睡眠报告类型  CONSUME_SLEEP_REPORT 消费级睡眠报告 HOSPITAL_SLEEP_REPORT 医疗级睡眠报告
        body.put("sleepReportType", "HOSPITAL_SLEEP_REPORT");
        body.put("sign", SignUtil.sign(body));
        log.info("body:{}", body.toString());
        Message<Map<String, Object>> message = flexolinkFeignClient.generateSleepReport(accessTokenVo.getAccessToken(), body);
        if (200 == message.getCode()) {
            //请求成功
            log.info("success:{}", message.toString());
        } else {
            //请求失败
            log.error("error:{}", message.toString());
        }
    }


}
