package com.flexolink.api.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 请求返回类
 * @param <T>
 */
@Data
public class Message<T> implements Serializable {

    private static final long serialVersionUID = -4158039679616095774L;
    /**
     * 状态码 200 请求成功
     */
    private Integer code;
    /**
     * 状态内容
     */
    private String msg;
    /**
     * 数据内容
     */
    private T data;
}
