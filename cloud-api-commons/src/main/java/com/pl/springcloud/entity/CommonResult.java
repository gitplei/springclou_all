package com.pl.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author plei
 * @date 2020/4/1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> {

    public CommonResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String message;
    private T data;

}
