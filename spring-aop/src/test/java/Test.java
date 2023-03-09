import com.june.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 代理的是接口
        UserService user = app.getBean("userService", UserService.class);
        user.add();
    }
}
