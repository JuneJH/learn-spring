package com.june.myspring.ioc;

public class RuntimeBeanReference {

    private String ref;

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public RuntimeBeanReference(String ref){
        this.ref = ref;
    }
}
