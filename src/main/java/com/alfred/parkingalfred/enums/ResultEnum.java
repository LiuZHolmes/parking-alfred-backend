package com.alfred.parkingalfred.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResultEnum {

    SUCCESS(HttpStatus.OK.value(), "success"),

    PARAM_ERROR(HttpStatus.BAD_REQUEST.value(), "incorrect parameters"),

    RESOURCES_NOT_EXISTED(HttpStatus.NOT_FOUND.value(), "resources not exists");

    private Integer status;

    private String message;

    ResultEnum(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
