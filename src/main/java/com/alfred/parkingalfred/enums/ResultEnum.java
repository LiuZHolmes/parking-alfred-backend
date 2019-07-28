package com.alfred.parkingalfred.enums;

public enum ResultEnum {
  SUCCESS(0, "成功"),
  PARAM_ERROR(1, "参数不正确"),
  EMPLOYEE_NOT_EXISTED(2,"员工不存在");
  private Integer status;
  private String message;

  ResultEnum(Integer status, String message) {
    this.status = status;
    this.message = message;
  }
}
