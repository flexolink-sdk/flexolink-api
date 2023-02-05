package com.flexolink.api.feign;

import com.flexolink.api.vo.AccessTokenVo;
import com.flexolink.api.vo.Message;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

/**
 * 柔灵开放平台 Api
 **/
@FeignClient(name = "flexolinkFeignClient", url = "${flexolink.openApi}")
public interface FlexolinkFeignClient {
    /**
     * 获取token授权
     */
    @PostMapping(value = "/api/auth/getToken")
    Message<AccessTokenVo> getToken(@RequestBody Map<String, Object> body);

    /**
     * 商家设备列表
     *
     * @param body {deviceName:设备名称 N
     *             deviceNumber:设备编号 N
     *             }
     */
    @PostMapping(value = "/api/shop/device/findByList")
    Message<Map<String, Object>> getShopList(@RequestHeader("token") String token,
                                             @RequestBody Map<String, Object> body);

    /**
     * 商家睡眠报告列表
     *
     * @param body {
     *             deviceNumber:设备编号 N
     *             sleepReportId:睡眠报告id N
     *             sleepReportType:睡眠报告类型 CONSUME_SLEEP_REPORT-小柔享睡
     * HOSPITAL_SLEEP_REPORT-额贴式睡眠监测软件 N
     *             }
     * @return{
     * 	"code": "200",
     * 	"msg": "success",
     * 	"data": {
     * 		"totalCount": 1,
     * 		"data": [
     *                        {
     * 				"id": 1,
     * 				"sleepReportId": 3525,
     * 				"edfUrl": "https://rlkj-test.oss-cn-shenzhen.aliyuncs.com/2022-12-14-153831-15111468543-Flex-AirDream1-3846.edf",
     * 				"sleepReportType": "CONSUME_SLEEP_REPORT",
     * 				"shopDeviceNumber": "Flex-BM05-500012"
     *            }
     * 		]
     * 	}
     * }
     */
    @PostMapping(value = "/api/shop/sleep/findByList")
    Message<Map<String, Object>> getSleepReportList(@RequestHeader("token") String token,
                                             @RequestBody Map<String, Object> body);

    /**
     * 查询商家指定设备
     *
     * @param token
     * @param body
     * @return{ "code": "200",
     * "msg": "success",
     * "data": {
     * "id": 643,
     * "deviceName": "123",
     * "deviceNumber": "Flex-BM01-010019",
     * "callbackUrl": "http://localhost:8015/api/shop/device/add",
     * "status": "N"
     * }
     * }
     */
    @PostMapping(value = "/api/shop/device/get")
    Message<Map<String, Object>> getDevice(@RequestHeader("token") String token,
                                           @RequestBody Map<String, Object> body);

    /**
     * 绑定设备
     *
     * @param token
     * @param body{ "deviceNumber":"Flex-BM01-010019" N,
     *              "deviceName":"设备名称" Y,
     *              "callbackUrl":"商户接收报告生成结果回调地址" N
     *              }
     * @return{ "code": "200",
     * "msg": "success",
     * "data": {
     * "id": 643,
     * "deviceName": "123",
     * "deviceNumber": "Flex-BM01-010019",
     * "callbackUrl": "http://localhost:8015/api/shop/device/add",
     * "status": "N"
     * }
     * }
     */
    @PostMapping(value = "/api/shop/device/add")
    Message<Map<String, Object>> addDevice(@RequestHeader("token") String token, @RequestBody Map<String, Object> body);

    /**
     * 修改绑定设备
     *
     * @param token
     * @param body{ "deviceNumber":"Flex-BM01-010019" N,
     *              "deviceName":"设备名称" Y,
     *              "callbackUrl":"商户接收报告生成结果回调地址" N
     *              }
     * @return{ "code": "200",
     * "msg": "success",
     * "data": "true"
     * }
     */
    @PostMapping(value = "/api/shop/device/set")
    Message<Boolean> setDevice(@RequestHeader("token") String token, @RequestBody Map<String, Object> body);

    /**
     * 删除（解除）绑定设备
     *
     * @param token
     * @param body{ "id":"1" Y,
     *              }
     * @return{ "code": "200",
     * "msg": "success",
     * "data": "true"
     * }
     */
    @PostMapping(value = "/api/shop/device/del")
    Message<Boolean> delDevice(@RequestHeader("token") String token,
                                           @RequestBody Map<String, Object> body);

    /**
     * 获取设备绑定的用户信息
     *
     * @param token
     * @param body{ "id":"1" Y,
     *              }
     * @return{ "code": "200",
     * "msg": "success",
     * "data": [
     * {
     * "id": 346,
     * "memberName": "小张",
     * "memberMobile": "13109262897"
     * }
     * ]
     * }
     */
    @PostMapping(value = "/api/shop/device/get/member/list")
    Message<Map<String, Object>> getMemberList(@RequestHeader("token") String token,
                                               @RequestBody Map<String, Object> body);

    /**
     * 获取商家睡眠报告明细
     *
     * @param token
     * @param body{ "id":"1" Y,
     *              }
     * @return {
     * 	"code": "200",
     * 	"msg": "success",
     * 	"data":  {
     * 		"sleepReportType": "CONSUME_SLEEP_REPORT",
     * 		"shopDeviceNumber": "Flex-BM05-500012",
     * 		"edfUrl": "https://rlkj-test.oss-cn-shenzhen.aliyuncs.com/2022-12-14-153831-15111468543-Flex-AirDream1-3846.edf",
     * 		"data": {
     * 			"sleepQuality": 0,
     * 			"averageSleepScore": 17,
     * 			"sleepStage": [
     * 				4,
     * 				4
     * 			],
     * 			"wakeDuration": 95,
     * 			"deepSleepDuration": 0,
     * 			"remDuration": 0,
     * 			"lightSleepDuration": 0,
     * 			"totalSleepDuration": 95,
     * 			"fallAsleepDuration": 95,
     * 			"fallAsleepTimePoint": "2022-12-14 14:36:44",
     * 			"brainAge": 15,
     * 			"sleepNeedFixIndex": 0,
     * 			"memoryConsolidateIndex": 0,
     * 			"sleepStableIndex": 83,
     * 			"brainRecoverIndex": 17,
     * 			"sleepImmerseCurve": [
     * 				1,
     * 				4.394601493707461e-8
     * 			],
     * 			"getupTimePoint": "2022-12-14 14:36:19",
     * 			"wakeTimesInSleep": 0,
     * 			"bodyMovementTimes": 0,
     * 			"bodyPosition": [
     * 				4,
     * 				4
     * 			],
     * 			"sleepImmerseTimes": 1,
     * 			"sleepImerseTime": [
     * 				0,
     * 				4
     * 			]
     *                }    * 	}
     * }
     */
    @PostMapping(value = "/api/shop/sleep/getPlatformSleepReport")
    Message<Map<String, Object>> getPlatformSleepReport(@RequestHeader("token") String token,
                                               @RequestBody Map<String, Object> body);
    /**
     * 生成端睡眠报告
     *
     * @param token
     * @param body{ "id":"1" Y,
     *              }
     * @return
     */
    @PostMapping(value = "/api/shop/sleep/generateSleepReport")
    Message<Map<String, Object>> generateSleepReport(@RequestHeader("token") String token,
                                               @RequestBody Map<String, Object> body);

}
