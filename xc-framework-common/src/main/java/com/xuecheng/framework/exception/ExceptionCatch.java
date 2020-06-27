package com.xuecheng.framework.exception;


import com.google.common.collect.ImmutableMap;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.model.response.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionCatch {

    private  static final Logger logger = LoggerFactory.getLogger(ExceptionCatch.class);

    //使用EXCEPTIONS存放异常类型和错误代码的映射，ImmutableMap的特点的一旦创建不可改变，并且线程安全
    private static ImmutableMap<Class<? extends Throwable>,ResultCode> EXCEOTIONS;

    //使用builder来构建一个异常类型和错误代码的异常
    protected static ImmutableMap.Builder<Class<? extends Throwable>,ResultCode> builder = ImmutableMap.builder();

    static{
        //在这里加入一些基础的异常类型判断
        builder.put(HttpMessageNotReadableException.class,CommonCode.INVALID_PARAM);
    }

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseResult customException( CustomException e){
        logger.error("catch exception:",e.getMessage(),e);
        return  new ResponseResult(e.getResultCode());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult Exception( CustomException e){
        logger.error("catch exception:",e.getMessage(),e);
        if(EXCEOTIONS == null){
            builder.build();
        }
        ResultCode code = EXCEOTIONS.get(e.getClass());
        if(code != null){
            return  new ResponseResult(code);
        }else{
            return  new ResponseResult(CommonCode.SERVER_ERROR);
        }

    }




}
