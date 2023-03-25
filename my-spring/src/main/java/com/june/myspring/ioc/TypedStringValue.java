package com.june.myspring.ioc;

public class TypedStringValue {
    private String value;

    private Class<?> targetValue;

    public Class<?> getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(Class<?> targetValue) {
        this.targetValue = targetValue;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
