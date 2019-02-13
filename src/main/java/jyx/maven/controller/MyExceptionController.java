package jyx.maven.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice//相当于专门写了一个异常处理的类，可以被项目中任意的方法使用
public class MyExceptionController {

    @ExceptionHandler({Exception.class})
    public String controllerException(Exception e) {
        //输出异常
        System.out.println(e + "Exception类可以处理所有异常");

        return "error";
    }
}
