package com.ypf.myTimeCost;

import org.springframework.lang.Nullable;

import java.lang.reflect.Method;

public interface TimeCostAttributeSource {
    default boolean isCandidateClass(Class<?> targetClass) {
        return true;
    }

    @Nullable
    TimeCostAttribute getTimeCostAttribute(Method method, @Nullable Class<?> targetClass);
}
