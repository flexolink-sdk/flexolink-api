package com.flexolink.api.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * AccessToken Vo
 */
@Data
public class AccessTokenVo implements Serializable {

    private static final long serialVersionUID = -355715676828232787L;
    /**
     * 授权的accessToken
     */
    private String accessToken;
    /**
     * 具体过期时间，精确到毫秒
     */
    private String expiredAt;


}
