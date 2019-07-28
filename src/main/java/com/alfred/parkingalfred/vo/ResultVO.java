package com.alfred.parkingalfred.vo;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO<T>implements Serializable {
  /** return code  */
  private Integer code;

  /** return message */
  private String message;

  /** return data */
  private T data;

}
