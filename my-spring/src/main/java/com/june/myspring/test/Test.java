package com.june.myspring.test;

import com.june.myspring.dao.UserDaoImpl;
import com.june.myspring.ioc.BeanDefinition;
import com.june.myspring.ioc.PropertyValue;
import com.june.myspring.ioc.RuntimeBeanReference;
import com.june.myspring.ioc.TypedStringValue;
import com.june.myspring.pojo.User;
import com.june.myspring.service.UserServiceImpl;
import org.apache.commons.dbcp.BasicDataSource;


import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.jsp.el.ImplicitObjectELResolver;

public class Test {
    // 存储BeanDefinition的容器
    private Map<String, BeanDefinition> beanDefinitions = new HashMap<>();
    // 存储单例Bean实例的Map容器
    private Map<String,Object> singletonObjects = new HashMap<>();

    public void test(){
        UserServiceImpl userService = new UserServiceImpl();
        UserDaoImpl userDao = new UserDaoImpl();

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://121.36.51.141:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        userDao.setDataSource(dataSource);
        userService.setUserDao(userDao);

        //实现用户查询功能
        Map<String, Object> map = new HashMap<>();
        map.put("name","june");
        List<User> users = userService.queryUserList(map);
        System.out.println("===="+users);
    }
    public void before(){
        String location = "beans.xml";
        InputStream is = getInputStream(location);
        Document document = createDocument(is);

        registerBeanDefinitions(document.getRootElement());

    }

    private void registerBeanDefinitions(Element rootElement){
        // 获取<bean>和自定义标签（比如mvc:interceptors）
        List<Element> elements = rootElement.elements();
        for (Element element : elements) {
            // 获取标签名称
            String name = element.getName();
            if (name.equals("bean")) {
                // 解析默认标签，其实也就是bean标签
                parseDefaultElement(element);
            } else {
                // 解析自定义标签，比如aop:aspect标签
                parseCustomElement(element);
            }
        }
    }

    private void parseCustomElement(Element element) {
    }

    private void parseDefaultElement(Element element) {
        try {
            if (beanElement == null) {
                return;
            }
            // 获取id属性
            String id = beanElement.attributeValue("id");

            // 获取name属性
            String name = beanElement.attributeValue("name");

            // 获取class属性
            String clazzName = beanElement.attributeValue("class");
            if (clazzName == null || "".equals(clazzName)) {
                return;
            }

            // 获取init-method属性
            String initMethod = beanElement.attributeValue("init-method");
            // 获取scope属性
            String scope = beanElement.attributeValue("scope");
            scope = scope != null && !scope.equals("") ? scope : "singleton";

            // 获取beanName
            String beanName = id == null ? name : id;
            Class<?> clazzType = Class.forName(clazzName);
            beanName = beanName == null ? clazzType.getSimpleName() : beanName;
            // 创建BeanDefinition对象
            // 此次可以使用构建者模式进行优化
            BeanDefinition beanDefinition = new BeanDefinition(clazzName, beanName);
            beanDefinition.setInitMethod(initMethod);
            beanDefinition.setScope(scope);
            // 获取property子标签集合
            List<Element> propertyElements = beanElement.elements();
            for (Element propertyElement : propertyElements) {
                parsePropertyElement(beanDefinition, propertyElement);
            }

            // 注册BeanDefinition信息
            this.beanDefinitions.put(beanName, beanDefinition);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void parsePropertyElement(BeanDefinition beanDefinition, Element propertyElement) {
        if (propertyElement == null)
            return;

        // 获取name属性
        String name = propertyElement.attributeValue("name");
        // 获取value属性
        String value = propertyElement.attributeValue("value");
        // 获取ref属性
        String ref = propertyElement.attributeValue("ref");

        // 如果value和ref都有值，则返回
        if (value != null && !value.equals("") && ref != null && !ref.equals("")) {
            return;
        }

        /**
         * PropertyValue就封装着一个property标签的信息
         */
        PropertyValue pv = null;

        if (value != null && !value.equals("")) {
            // 因为spring配置文件中的value是String类型，而对象中的属性值是各种各样的，所以需要存储类型
            TypedStringValue typeStringValue = new TypedStringValue(value);

            Class<?> targetType = getTypeByFieldName(beanDefination.getClazzName(), name);
            typeStringValue.setTargetType(targetType);

            pv = new PropertyValue(name, typeStringValue);
            beanDefination.addPropertyValue(pv);
        } else if (ref != null && !ref.equals("")) {

            RuntimeBeanReference reference = new RuntimeBeanReference(ref);
            pv = new PropertyValue(name, reference);
            beanDefination.addPropertyValue(pv);
        } else {
            return;
        }
    }

    private Document createDocument(InputStream is) {
        Document document = null;
        try {
            SAXReader reader = new SAXReader();
            document = reader.read(is);
            return document;
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Class<?> getTypeByFieldName(String beanClassName, String name) {
        try {
            Class<?> clazz = Class.forName(beanClassName);
            Field field = clazz.getDeclaredField(name);
            return field.getType();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private InputStream getInputStream(String location) {
        return this.getClass().getClassLoader().getResourceAsStream(location);
    }

    @Test
    public void test2(){

    }
}
