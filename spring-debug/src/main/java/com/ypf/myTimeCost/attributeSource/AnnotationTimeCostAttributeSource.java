package com.ypf.myTimeCost.attributeSource;

import com.ypf.myTimeCost.attribute.TimeCostAttribute;
import com.ypf.myTimeCost.annotationParse.SpringTimeCostAnnotationParser;
import com.ypf.myTimeCost.annotationParse.TimeCostAnnotationParser;
import org.springframework.core.MethodClassKey;
import org.springframework.lang.Nullable;
import org.springframework.transaction.interceptor.TransactionAttribute;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class AnnotationTimeCostAttributeSource implements TimeCostAttributeSource {

    private final Map<Object, TimeCostAttribute> attributeCache = new ConcurrentHashMap<>(1024);
    private final Set<TimeCostAnnotationParser> annotationParsers;

    public AnnotationTimeCostAttributeSource() {
        this.annotationParsers = new LinkedHashSet<>(1);
        annotationParsers.add(new SpringTimeCostAnnotationParser());
    }

    @Override
    public TimeCostAttribute getTimeCostAttribute(Method method, Class<?> clazz) {
        if (method.getDeclaringClass() == Object.class) {
            return null;
        }
        Object cacheKey = getCacheKey(method, clazz);
        TimeCostAttribute cached = attributeCache.get(cacheKey);
        if (cached != null) {
            return cached;
        }
        TimeCostAttribute tcAttr = computeTimeCostAttribute(method, clazz);
        if (tcAttr != null) {
            attributeCache.put(cacheKey, tcAttr);
        }
        return tcAttr;
    }

    protected TimeCostAttribute computeTimeCostAttribute(Method method, Class<?> clazz) {
        TimeCostAttribute tcAttr = findTimeCostAttribute(method);
        if (tcAttr != null) {
            return tcAttr;
        }
        return findTimeCostAttribute(clazz);
    }

    @Override
    public TimeCostAttribute findTimeCostAttribute(Class<?> clazz) {
        return determineTimeCostAttribute(clazz);
    }

    @Override
    public TimeCostAttribute findTimeCostAttribute(Method method) {
        return determineTimeCostAttribute(method);
    }

    @Nullable
    protected TimeCostAttribute determineTimeCostAttribute(AnnotatedElement element) {
        // 获取我们的注解解析器
        for (TimeCostAnnotationParser parser : this.annotationParsers) {
            // 通过注解解析器去解析我们的元素(方法或者类)上的注解
            TimeCostAttribute attr = parser.parseTimeCostAnnotation(element);
            if (attr != null) {
                return attr;
            }
        }
        return null;
    }

    protected Object getCacheKey(Method method, @Nullable Class<?> targetClass) {
        return new MethodClassKey(method, targetClass);
    }
}
