package com.ypf.myTimeCost;

import com.ypf.myTimeCost.anno.TimeCostal;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.transaction.interceptor.TransactionAttribute;

import java.lang.reflect.AnnotatedElement;

public class SpringTimeCostAnnotationParser implements TimeCostAnnotationParser{
    @Override
    public TimeCostAttribute parseTransactionAnnotation(AnnotatedElement element) {
        AnnotationAttributes attributes = AnnotatedElementUtils.findMergedAnnotationAttributes(element, TimeCostal.class, false, false);
        if (attributes != null) {
            return parseTimeCostAnnotation(attributes);
        } else {
            return null;
        }
    }

    protected TimeCostAttribute parseTimeCostAnnotation(AnnotationAttributes attributes) {
        return new DefaultTimeCostAttribute();
    }
}
