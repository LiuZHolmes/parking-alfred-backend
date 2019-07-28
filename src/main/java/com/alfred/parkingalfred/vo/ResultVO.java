package com.alfred.parkingalfred.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO<T> implements Serializable {

    private Integer code;

    private String message;

    private T data;
}
