import com.june.learn.ConstructorArg;
import com.june.learn.Hello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        // new 方式获取
        Hello h1 = new Hello();
        h1.sayHello();
        // spring 方式获取
        ApplicationContext app = new ClassPathXmlApplicationContext("bean.xml");
        Hello h2 = (Hello) app.getBean("hello");
        h2.sayHello();

        System.out.println("====构造函数注入=======");
        ConstructorArg ca = (ConstructorArg)app.getBean("ConstructorArg");
        ca.sayHello();
    }
}
