package com.ypf.myTimeCost;

import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Ejb3TransactionAnnotationParser;
import org.springframework.transaction.annotation.JtaTransactionAnnotationParser;
import org.springframework.transaction.annotation.SpringTransactionAnnotationParser;
import org.springframework.transaction.annotation.TransactionAnnotationParser;
import org.springframework.transaction.interceptor.TransactionAttribute;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class AnnotationTimeCostAttributeSource implements TimeCostAttributeSource{
    private final Set<TimeCostAnnotationParser> annotationParsers;
    public AnnotationTimeCostAttributeSource() {
        this.annotationParsers = new LinkedHashSet<>(1);
        annotationParsers.add(new SpringTimeCostAnnotationParser());
    }
    @Override
    public TimeCostAttribute getTimeCostAttribute(Method method, Class<?> clazz) {
        return determineTimeCostAttribute(clazz);
    }

    @Nullable
    protected TimeCostAttribute determineTimeCostAttribute(AnnotatedElement element) {
        // 获取我们的注解解析器
        for (TimeCostAnnotationParser parser : this.annotationParsers) {
            // 通过注解解析器去解析我们的元素(方法或者类)上的注解
            TimeCostAttribute attr = parser.parseTransactionAnnotation(element);
            if (attr != null) {
                return attr;
            }
        }
        return null;
    }
}
