package com.ypf.myTimeCost.annotationParse;

import com.ypf.myTimeCost.attribute.TimeCostAttribute;
import org.springframework.lang.Nullable;

import java.lang.reflect.AnnotatedElement;

public interface TimeCostAnnotationParser {
    default boolean isCandidateClass(Class<?> targetClass) {
        return true;
    }

    @Nullable
    TimeCostAttribute parseTimeCostAnnotation(AnnotatedElement element);
}
