package jyx.maven.controller;

import jyx.maven.entity.JsonStudent;
import jyx.maven.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SessionAttributes(value = "student4")
//如果要将student4放入request的同时放入Session中，加上此注释即可(可以放多个，示例：@SessionAttributes(value = "student4,student3"))
//@SessionAttributes(types = Student.class)//如果要将所有Student实体类放入request的同时放入Session中，加上此注释即可(可以放多个,示例：@SessionAttributes(type = {Student.class,Address.class}))
@Controller
//在struts2中需要我们自己配置struts.xml中的action来实现，或许struts2中也可以用注释来实现，但反正我不知道
@RequestMapping("springmvc")//交给springmvc中的servlet处理后，他们的映射关系就是通过@RequestMapping这个注释来实现的
public class SpringMvcController {

    //只拦截对应method方式
    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome() {

        return "success";   //默认使用的是请求转发的跳转方式
    }

    //请求头必须符合要求，至于啥是请求头，别问，问就是不知道
    @RequestMapping(value = "/welcome2", headers = {""})
    public String welcome2() {

        return "success";   //默认使用的是请求转发的跳转方式
    }

    /*
        普及一个小小的知识点：?    代表一个单字符
                              *    代表任意字符串
                              **   代表任意目录
     */
    @RequestMapping(value = "/welcome3/a?c")
    public String welcome3() {

        return "success";   //默认使用的是请求转发的跳转方式
    }

    @RequestMapping(value = "/welcome4/*")
    public String welcome4() {

        return "success";   //默认使用的是请求转发的跳转方式
    }

    @RequestMapping(value = "/welcome5/**/abc")
    public String welcome5() {

        return "success";   //默认使用的是请求转发的跳转方式
    }

    //请求中的参数必须符合以下要求：1.必须要有name且等于lll；2.age属性可以没有，若有，必须不等于23；3.请求参数中不能有bir属性
    @RequestMapping(value = "/welcome6", params = {"name=lll", "age!=23", "!bir"})
    public String welcome6() {

        return "success";   //默认使用的是请求转发的跳转方式
    }

    //通过@PathVariable获取动态参数
    @RequestMapping(value = "/welcome7/{name}")
    public String welcome7(@PathVariable("name") String name) {

        System.out.println(name);

        return "success";   //默认使用的是请求转发的跳转方式
    }

    //增：post
    @RequestMapping(value = "/testPost/{test}")
    public String testPost(@PathVariable("test") String test) {
        System.out.println("增：" + test);
        return "success";                                           //增
    }

    //删：DELETE                                                              没有实现此功能，据说在tomcat8.0之后不支持此功能
    @RequestMapping(value = "/testDelete/{test}")
    public String testDelete(@PathVariable("test") String test) {
        System.out.println("删：" + test);
        return "success";                                           //删
    }

    //改：PUT                                                                  没有实现此功能，据说在tomcat8.0之后不支持此功能
    @RequestMapping(value = "/testPut/{test}")
    public String testPut(@PathVariable("test") String test) {
        System.out.println("改：" + test);
        return "success";                                           //改
    }

    //查：get
    @RequestMapping(value = "/testGet/{test}")
    public String testGet(@PathVariable("test") String test) {
        System.out.println("查：" + test);
        return "success";                                           //查
    }

    //spring mvc中通过注释来获取参数，是真的方便，我试过中文了，可以的
    @RequestMapping(value = "/testParam")
    //@RequestParam前端过来的参数可以多，但是后台接收的参数一定不能多
    //@RequestParam当只有一个值的时候，其他的属性都是默认值
    //      required属性：这个参数是否为必须的，默认为true
    //      defaultValue属性：设置这个参数的默认值，只在required属性设置为false时才生效
    //      当设置了其他的属性的时候，我们要严格的按照key,value这种键值对的形式来填写
    public String testParam(@RequestParam(value = "test", defaultValue = "lll") String test, @RequestParam(value = "test2", required = false, defaultValue = "lll") String test2) {
        //这个是原始的方法
        //String test = request.getParameter("test")

        System.out.println("Param = " + test);

        System.out.println("Param = " + test2);
        return "success";
    }

    //获取请求头
    @RequestMapping(value = "/testRequestHeader")
    public String testRequestHeader(@RequestHeader("Host") String header) {

        System.out.println("请求头：" + header);

        return "success";
    }

    //获取请求头
    @RequestMapping(value = "/testCookieValue")
    public String testCookieValue(@CookieValue("JSESSIONID") String JSESSIONID) {

        System.out.println("JSESSIONID = " + JSESSIONID);

        return "success";
    }

    //获取封装后的实体类对象，前提：实体对象中的属性名和前端页面中的属性名必须一致，支持级联属性（就是例子中的 address.homeAddress 这个玩意儿）
    @RequestMapping(value = "/testObjectProperties")
    public String testObjectProperties(Student student) {

        System.out.println(student);//此方法会产生中文乱码，未解决

        return "success";
    }

    //使用原生的ServletAPI,需要对应的jar包：Java Servlet API
    @RequestMapping(value = "/testServletAPI")
    public String testServletAPI(HttpServletRequest request) {
        String id = request.getParameter("id");
        String name = request.getParameter("name");

        System.out.println("id：" + id);
        System.out.println("姓名：" + name);           //依旧有中文乱码
        return "success";
    }

    //后台向前端传递数据，方法1
    @RequestMapping(value = "/testModelAndView")
    public ModelAndView testModelAndView() {           //中文成功
        ModelAndView mv = new ModelAndView("success");//这里是指要把数据传到哪个页面中

        Student student = new Student();

        student.setId(2);
        student.setName("江云翔");
        //将数据放到request作用域中
        mv.addObject("student1", student);//相当于request.setAttribute("student",student);

        return mv;
    }

    //后台向前端传递数据，方法2
    @RequestMapping(value = "/testModelMap")
    public String testModelMap(ModelMap modelMap) {           //中文成功

        Student student = new Student();

        student.setId(2);
        student.setName("江云翔");
        //将数据放到request作用域中
        modelMap.put("student2", student);

        return "success";
    }

    //后台向前端传递数据，方法3
    @RequestMapping(value = "/testMap")
    public String testMap(Map map) {           //中文成功

        Student student = new Student();

        student.setId(2);
        student.setName("江云翔");
        //将数据放到request作用域中
        map.put("student3", student);

        return "success";
    }

    //后台向前端传递数据，方法4
    @RequestMapping(value = "/testModel")
    public String testModel(Model model) {           //中文成功

        Student student = new Student();

        student.setId(2);
        student.setName("江云翔");
        //将数据放到request作用域中
        model.addAttribute("student4", student);

        return "success";
    }

//    //在调用 任何 请求前，都会执行 @ModelAttribute 修饰的方法。因为是任何请求，所以慎用
//    @ModelAttribute//一般使用在修改操作中，这个在写J2EE大作业的时候我深有体会
//    public void queryStudentById(Map<String,Object> map){
//        //在实际情况中应该是按照三层结构来实现的，这里只是测试，所以就省略了
//        //通过service层调用逻辑
//        //···
//
//        Student student = new Student();
//        student.setId(1);
//        student.setName("啦啦啦");
//
//        //map.put("student",student);//约定1：map的key是对应实体类的类名的首字母小写
//        map.put("stu",student);//约定2：map的key是随意，则在对应的方法中参数的部分要加上相应的注释
//    }
//
//    //测试@ModelAttribute
//    @RequestMapping(value = "/testModelAttribute")
//    //public String testModelAttribute(Student student) {     //对应约定1
//    public String testModelAttribute(@ModelAttribute("stu")Student student) {     //对应约定2
//
//        //测试是否接收到了来自@ModelAttribute注释修饰的方法中的数据
//        System.out.println(student);
//        //修改数据
//        student.setName("江云翔");
//        //测试结果
//        System.out.println(student);
//
//        return "success";
//    }

    //国际化，有中文乱码
    @RequestMapping(value = "/testI18n")
    public String testI18n() {

        return "success";
    }

    //我们可以不用@RequestMapping来实现页面的跳转，是需要在XML中配置
    public void testViewController() {

    }

    /*默认情况下使用的是请求转发的跳转方式，这个当然是可以改的
            forward     转发
            redirect    重定向
      修改方式是是下面的示例，注意：在使用了下面这种的方式的时候，最初设置的前缀和后缀是没用的
    */
    //forward方式
    @RequestMapping(value = "/testForward")
    public String testForward() {
        //自制错误
        System.out.println(1 / 0);
        return "forward:/view/success.jsp";
    }

    //redirect方式
    @RequestMapping(value = "/testRedirect")
    public String testRedirect() {

        return "redirect:/view/success.jsp";
        //return "redirect:testConverter";//我们也可以跳转到自己写的方法中去
    }

    //测试自定义的转换器，测试数据"1-lll-22"
    @RequestMapping(value = "/testConverter")
    //自定义转换器的触发需要此注释，自定义转换器会自己匹配接收到的数据来判断使用哪个转换器
    public String testConverter(@RequestParam("studentInfo") Student student) {         //中文乱码

        System.out.println(student);
        return "success";
    }

    //测试日期数据格式化"1998-03-06"
    @RequestMapping(value = "/testDateTimeFormat")                          //将错误信息传到前端去
    public String testDateTimeFormat(@Valid Student student, BindingResult result, Map<String, Object> map) {         //当参数转换失败的时候，SpringMVC会自动将错误信息BindingResult这个类中，这个很重要
        //为需要校验的参数添加相关的注释    // 注意这里的参数顺序不能变，因为BindingResult存储的是前一个参数错误的错误信息
        if (result.getErrorCount() > 0) {
            for (FieldError error : result.getFieldErrors()) {
                System.out.println(error.getDefaultMessage());
                map.put("errors", result.getFieldErrors());
            }
        }

        System.out.println(student);

        return "success";
    }

    @ResponseBody   //作用：告诉程序此时返回的不是一个View页面，而是一个 Json数组 ,好好用，实现前后端数据传输的关键
    @RequestMapping(value = "/testJson")
    public List<JsonStudent> testJson() {               //中文可以
        //在实际应用中应该按照controller-service-dao这样的三层结构，以下一切从简

        //直接创建3个学生对象
        JsonStudent student1 = new JsonStudent(1, "zs", 23);
        JsonStudent student2 = new JsonStudent(2, "ls", 24);
        JsonStudent student3 = new JsonStudent(3, "ww", 25);
        JsonStudent student4 = new JsonStudent(4, "江云翔", 22);//中文测试，通过
        //新建一个数组用于存放学生
        List<JsonStudent> students = new ArrayList<>();
        //将创建的学生对象放入到学生数组当中去
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);

        return students;//实际上返回的是一个Json数组
    }

    //测试 前端->后端->前端
    @RequestMapping(value = "/testJson2")
    public String testJson2(JsonStudent student, BindingResult result) {

        if (result.getErrorCount() > 0) {
            for (FieldError error : result.getFieldErrors()) {
                System.out.println(error.getDefaultMessage());
            }
        }

        System.out.println(student);

        return "testJson";
    }

    //文件上传
    @RequestMapping(value = "/testUpload")      //乱不乱码还不确定，任意文件均可，但不要太大，不然太复杂
    public String testUpload(@RequestParam("desc") String desc, @RequestParam("file") MultipartFile file) throws Exception {
        //输出文件描述
        System.out.println("文件描述信息：" + desc);
        //开启输入流
        InputStream input = file.getInputStream();
        //获取文件上传时的名字
        String filename = file.getOriginalFilename();
        //开启输出流，设置文件存储位置
        OutputStream out = new FileOutputStream("d:\\" + filename);//存储地址可变
        //设置最大缓冲
        byte[] bs = new byte[1024];

        int len = -1;

        while ((len = input.read(bs)) != -1) {
            out.write(bs, 0, len);
        }
        //关闭输入输出流
        out.close();
        input.close();
        //标志成功
        System.out.println("上传成功");
        return "success";
    }
}
