package com.ygr.sell.util;

import com.ygr.sell.VO.ResultVO;

public class ResultVOUtil {
    public static ResultVO success(Object object){
        ResultVO result = new ResultVO();
        result.setData(object);
        result.setStatus("成功");
        result.setCode(0);
        return result;
    }
    public static ResultVO success(){
        return success(null);
    }
    public static ResultVO error(Integer code,String massge){
        ResultVO result = new ResultVO();
        result.setCode(code);
        result.setStatus(massge);
        return result;
    }

}
