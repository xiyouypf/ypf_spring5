package com.ypf.myTimeCost;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.AopUtils;

public class TimeCostInterceptor implements MethodInterceptor {
    private TimeCostAttributeSource TimeCostAttributeSource;
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        // 获取我们的代理对象的class属性
        Class<?> targetClass = (invocation.getThis() != null ? AopUtils.getTargetClass(invocation.getThis()) : null);
        System.out.println("前置通知");
        Object retVal = invocation.proceed();
        System.out.println("后置通知");
        return retVal;
    }

    public TimeCostAttributeSource getTimeCostAttributeSource() {
        return TimeCostAttributeSource;
    }

    public void setTimeCostAttributeSource(TimeCostAttributeSource timeCostAttributeSource) {
        TimeCostAttributeSource = timeCostAttributeSource;
    }
}
