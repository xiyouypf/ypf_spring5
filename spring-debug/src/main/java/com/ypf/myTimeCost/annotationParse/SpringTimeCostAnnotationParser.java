package com.ypf.myTimeCost.annotationParse;

import com.ypf.myTimeCost.attribute.DefaultTimeCostAttribute;
import com.ypf.myTimeCost.attribute.TimeCostAttribute;
import com.ypf.myTimeCost.anno.TimeCostal;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAttributes;

import java.lang.reflect.AnnotatedElement;

public class SpringTimeCostAnnotationParser implements TimeCostAnnotationParser {
    @Override
    public TimeCostAttribute parseTimeCostAnnotation(AnnotatedElement element) {
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
