# **快速接入**
### #获取AppKey&AppSecret
商家需要使用AppKey&AppSecret来作为调用Api接口的主要凭证。
获取方式：请联系业务人员获取。
### #sign签名
所有接口调用都需要sign参数,详情参考sign签名规则
### #token获取方式
除授权接口外，所有接口都需要在header中传输token作为商户授权的唯一凭证。
token获取方式：[《获取授权》](https://www.yuque.com/rongjie-m6cw5/xvsuc9/ih7qge?singleDoc#%20%E3%80%8A%E8%8E%B7%E5%8F%96%E6%8E%88%E6%9D%83%E3%80%8B)
### #Api请求地址：
https://openapi.flexolinkai.com/

Java示例
```
 AccessTokenParam accessTokenParam = new AccessTokenParam();
        // 商家端appKey
        accessTokenParam.setAppKey(APP_KEY);
        // 商家端appSecret
        accessTokenParam.setAppSecret(APP_SECRET);
        Map<String, Object> body = SignUtil.toMap(accessTokenParam);
			  // 签名
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
```