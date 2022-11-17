package com.ypf.myTimeCost;

import org.springframework.lang.Nullable;

import java.lang.reflect.AnnotatedElement;

public interface TimeCostAnnotationParser {
    default boolean isCandidateClass(Class<?> targetClass) {
        return true;
    }
    @Nullable
    TimeCostAttribute parseTransactionAnnotation(AnnotatedElement element);
}
