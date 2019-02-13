package jyx.maven.converter;

import jyx.maven.entity.Student;
import org.springframework.core.convert.converter.Converter;

/**
 * 自定义转换器            接收到的数据类型,要转换的数据类型
 * 1.编写自定义转换器的类（实现Converter<S,T>接口）
 * 2.将自定义的转换器加入到SpringMVC中
 */
public class MyConverter implements Converter<String, Student> {


    @Override
    public Student convert(String source) {
        //1.接收前端传进来的数据，例：2-test-22
        String[] studentStrArr = source.split("-");

        //新建一个实体对象
        Student student = new Student();

        //为新的实体对象添加数据
        student.setId(Integer.parseInt(studentStrArr[0]));
        student.setName(studentStrArr[1]);
        student.setAge(Integer.parseInt(studentStrArr[2]));

        //返回实体对象
        return student;
    }
}
