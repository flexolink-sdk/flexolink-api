package com.flexolink.api.param;

import lombok.Data;

import java.io.Serializable;

/**
 * AccessTokenParam
 */
@Data
public class AccessTokenParam implements Serializable {

    /**
     * 授权的key
     */
    private String appKey;
    /**
     * 授权的密钥
     */
    private String appSecret;
}
