package com.alfred.parkingalfred.vo;

import java.io.Serializable;
import lombok.Data;

@Data
public class ResultVO<T>implements Serializable {
  /** return code  */
  private Integer code;

  /** return message */
  private String message;

  /** return data */
  private T data;
}
