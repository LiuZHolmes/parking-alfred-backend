package com.alfred.parkingalfred.enums;

import lombok.Data;
import org.springframework.http.HttpStatus;


public enum ResultEnum {
  SUCCESS(HttpStatus.OK.value(), "成功"),
  PARAM_ERROR(HttpStatus.BAD_REQUEST.value(), "参数不正确"),
  RESOURCES_NOT_EXISTED(HttpStatus.NOT_FOUND.value(),"资源不存在");
  private Integer status;
  private String message;

  ResultEnum(Integer status, String message) {
    this.status = status;
    this.message = message;
  }

  public Integer getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }
}
