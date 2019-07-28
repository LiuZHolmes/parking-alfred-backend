package com.alfred.parkingalfred.utils;

import com.alfred.parkingalfred.vo.ResultVO;
import org.springframework.http.HttpStatus;

public class ResultVOUtil {
  public static ResultVO success(Object object) {
    ResultVO resultVo = new ResultVO();
    resultVo.setData(object);
    resultVo.setCode(HttpStatus.OK.value());
    resultVo.setMessage("成功");
    return resultVo;
  }

  public static ResultVO success() {
    return success(null);
  }

  public static ResultVO error(Integer code, String msg) {
    ResultVO resultVo = new ResultVO();
    resultVo.setCode(code);
    resultVo.setMessage(msg);
    return resultVo;
  }
}
