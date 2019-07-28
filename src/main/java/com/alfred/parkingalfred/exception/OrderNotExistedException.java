package com.alfred.parkingalfred.exception;

import com.alfred.parkingalfred.enums.ResultEnum;

public class OrderNotExistedException extends RuntimeException {

    private Integer code;

    public OrderNotExistedException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getStatus();
    }

    public OrderNotExistedException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
