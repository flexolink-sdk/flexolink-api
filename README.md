
# 柔灵API简介

### #API概述
柔灵API 是柔灵产品提供的开放应用程序接口，是除了商家后台外，主要的服务形式之一。开发者可以通过编程的方式来使用柔灵科技的服务，相比于商家后台，使用柔灵API能跟便捷的将开发者的产品与柔灵产品深度结合 。柔灵API为开发者提供了多种编程语言（Java、Python、Node.js/TypeScript、PHP、C++ 等）的SDK示例、这些 SDK都是基于柔灵API的能力而构建。

柔灵 API 以访问密钥（appKey&appSecret）识别调用者身份，提供自动签名等功能，方便您通过API接入柔灵产品来获取各项数据。使用API功能需要您购买柔灵脑电产品，并申请appKey和appSecret。






# **快速接入**
### #获取AppKey&AppSecret
商家需要使用AppKey&AppSecret来作为调用Api接口的主要凭证。
获取方式：请联系业务人员获取。

 网址：<u>www.flexolinkai.com</u>

 联系电话：0571-56121906

 邮箱：<u>market@flexolinktech.com</u>



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