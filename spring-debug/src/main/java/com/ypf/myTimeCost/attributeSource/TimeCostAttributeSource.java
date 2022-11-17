package com.ypf.myTimeCost.attributeSource;

import com.ypf.myTimeCost.attribute.TimeCostAttribute;
import org.springframework.lang.Nullable;
import org.springframework.transaction.interceptor.TransactionAttribute;

import java.lang.reflect.Method;

public interface TimeCostAttributeSource {
    default boolean isCandidateClass(Class<?> targetClass) {
        return true;
    }

    @Nullable
    TimeCostAttribute getTimeCostAttribute(Method method, @Nullable Class<?> targetClass);

    @Nullable
    TimeCostAttribute findTimeCostAttribute(Class<?> clazz);

    @Nullable
    TimeCostAttribute findTimeCostAttribute(Method method);
}
