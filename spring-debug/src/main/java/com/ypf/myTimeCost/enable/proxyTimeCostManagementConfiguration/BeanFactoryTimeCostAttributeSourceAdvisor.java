package com.ypf.myTimeCost;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;

@SuppressWarnings("serial")
public class BeanFactoryTimeCostAttributeSourceAdvisor extends AbstractBeanFactoryPointcutAdvisor {

    private TimeCostAttributeSource timeCostAttributeSource;
    private final TimeCostAttributeSourcePointcut pointcut = new TimeCostAttributeSourcePointcut() {
        @Override
        protected TimeCostAttributeSource getTimeCostAttributeSource() {
            return timeCostAttributeSource;
        }
    };

    public void setTimeCostAttributeSource(TimeCostAttributeSource timeCostAttributeSource) {
        this.timeCostAttributeSource = timeCostAttributeSource;
    }

    public void setClassFilter(ClassFilter classFilter) {
        this.pointcut.setClassFilter(classFilter);
    }

    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }
}
