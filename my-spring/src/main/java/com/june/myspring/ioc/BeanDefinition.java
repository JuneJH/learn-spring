package com.june.myspring.ioc;

import java.util.ArrayList;
import java.util.List;

public class BeanDefinition {
    private String clazzName;
    private Class<?> clazzType;
    private String beanName;
    private String initMethod;
    private String scope;

    private List<PropertyValue> propertyValueList = new ArrayList<>();

    private static final String SCOPE_SINGLETON = "singleton";
    private static final String SCOPE_PROTOTYPE = "prototype";

    public BeanDefinition(String clazzName,String beanName){
        this.clazzName = clazzName;
        this.beanName = beanName;
        this.clazzType = resolveClassName(clazzName);
    }

    private Class<?> resolveClassName(String clazzName) {
        try {
            return Class.forName(clazzName);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getInitMethod() {
        return initMethod;
    }

    public void setInitMethod(String initMethod) {
        this.initMethod = initMethod;
    }

    public List<PropertyValue> getPropertyValueList() {
        return propertyValueList;
    }

    public void addPropertyValue(PropertyValue p) {
        this.propertyValueList.add(p);
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Class<?> getClazzType() {
        return clazzType;
    }

    public void setClazzType(Class<?> clazzType) {
        this.clazzType = clazzType;
    }

    public boolean isSingleton (){
        return  SCOPE_SINGLETON.equals(this.scope);
    }

    public boolean isPrototype(){
        return SCOPE_PROTOTYPE.equals(this.scope);
    }


}
