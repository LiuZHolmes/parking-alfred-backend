package com.alfred.parkingalfred.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    SUCCESS(200, "成功"),
    PARAM_ERROR(400, "参数不正确"),
    RESOURCES_NOT_EXISTED(404, "资源不存在");

    private Integer status;

    private String message;

    ResultEnum(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
