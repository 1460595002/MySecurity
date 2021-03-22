package cn.jinronga.exception;

import cn.jinronga.util.R;
import cn.jinronga.util.ResultCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zn
 * @date
 */
@ControllerAdvice
public class GlobalExceptionResolver {

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public R businessExceptionHandler(BusinessException e){
        // 输出日志  文件   数据库
        e.printStackTrace();
        return R.of(e.getCode(),e.getMsg());
    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R exceptionHandler(Exception e){
        e.printStackTrace();
        return R.of(ResultCode.UNKNOWN_ERROR);
    }

}
