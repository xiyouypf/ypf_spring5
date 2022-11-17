package com.ypf.myTimeCost.enable.proxyTimeCostManagementConfiguration;

import com.ypf.myTimeCost.attributeSource.TimeCostAttributeSource;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Method;

@SuppressWarnings("serial")
public abstract class TimeCostAttributeSourcePointcut extends StaticMethodMatcherPointcut {
    public TimeCostAttributeSourcePointcut() {
        setClassFilter(new TimeCostAttributeSourceClassFilter());
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        TimeCostAttributeSource tas = getTimeCostAttributeSource();
        return (tas == null || tas.getTimeCostAttribute(method, targetClass) != null);
    }

    @Nullable
    protected abstract TimeCostAttributeSource getTimeCostAttributeSource();

    private class TimeCostAttributeSourceClassFilter implements ClassFilter {

        @Override
        public boolean matches(Class<?> clazz) {
            TimeCostAttributeSource tas = getTimeCostAttributeSource();
            return (tas == null || tas.isCandidateClass(clazz));
        }
    }


    @Override
    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TimeCostAttributeSourcePointcut)) {
            return false;
        }
        TimeCostAttributeSourcePointcut otherPc = (TimeCostAttributeSourcePointcut) other;
        return ObjectUtils.nullSafeEquals(getTimeCostAttributeSource(), otherPc.getTimeCostAttributeSource());
    }

    @Override
    public int hashCode() {
        return TimeCostAttributeSourcePointcut.class.hashCode();
    }

    @Override
    public String toString() {
        return getClass().getName() + ": " + getTimeCostAttributeSource();
    }
}