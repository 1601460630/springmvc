package jyx.maven.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("second")
public class SpringMvcExceptionController {

    @RequestMapping("testExceptionHandler")
    public String testExceptionHandler() {
        //制造异常
        System.out.println(1 / 0);
        return "success";
    }

    //改方法可以捕获 ArithmeticException 类异常，这玩意儿吧，我觉得要讲究大道至简，直接写个通用的异常处理就可以了 Exception.class 就是这样的，没错可行性测试，只是一个想法
    @ExceptionHandler({ArithmeticException.class})  //注意：当存在一个异常，有两个方法都可以处理时，用就近原则
    public String controllerArithmeticException(ArithmeticException e) {     //里面的参数一定是要异常类型的参数，就是 *Exception 这样的，而且不能有其他的参数
        //输出异常
        System.out.println(e);

        return "error";
    }

    //我们也可以自定义错误信息，但我觉得那个东西不是很重要，我就没写了
    //异常信息可以在xml中配置，我觉得不重要，就懒得写了
}
