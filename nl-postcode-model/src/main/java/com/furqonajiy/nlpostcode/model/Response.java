package com.furqonajiy.nlpostcode.model;

import lombok.Data;

@Data
public class Response<T> {
    private String statusCode;
    private String statusDesc;
    private T data;
}
