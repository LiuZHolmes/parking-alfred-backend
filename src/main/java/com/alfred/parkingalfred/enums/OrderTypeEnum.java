package com.alfred.parkingalfred.enums;

import lombok.Getter;

@Getter
public enum OrderTypeEnum implements BaseEnum {

    PARK_CAR(1, "PARK_CAR"),
    FETCH_CAR(2, "FETCH_CAR");

    private Integer code;

    private String message;

    OrderTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
