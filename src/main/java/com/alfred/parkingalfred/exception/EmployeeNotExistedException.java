package com.alfred.parkingalfred.exception;

import com.alfred.parkingalfred.enums.ResultEnum;
import java.util.function.Supplier;

public class EmployeeNotExistedException extends RuntimeException  {

  private Integer code;

  public EmployeeNotExistedException(ResultEnum resultEnum) {
    super(resultEnum.getMessage());

    this.code = resultEnum.getStatus();
  }
  public EmployeeNotExistedException(Integer code,String message){
    super(message);
    this.code=code;
  }

}
