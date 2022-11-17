package com.ypf.myTimeCost;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.lang.Nullable;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.interceptor.TransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionalProxy;
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
//            if (TransactionalProxy.class.isAssignableFrom(clazz) ||
//                    TransactionManager.class.isAssignableFrom(clazz) ||
//                    PersistenceExceptionTranslator.class.isAssignableFrom(clazz)) {
//                return false;
//            }
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
