package com.ypf.myTimeCost;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class ProxyTimeCostManagementConfiguration {
    @Bean
    public BeanFactoryTimeCostAttributeSourceAdvisor beanFactoryTimeCostAttributeSourceAdvisor(
            TimeCostAttributeSource timeCostAttributeSource,
            TimeCostInterceptor timeCostInterceptor) {
        BeanFactoryTimeCostAttributeSourceAdvisor advisor = new BeanFactoryTimeCostAttributeSourceAdvisor();
        advisor.setTimeCostAttributeSource(timeCostAttributeSource);
        advisor.setAdvice(timeCostInterceptor);
        return advisor;
    }

    @Bean
    public TimeCostAttributeSource timeCostAttributeSource() {
        return new AnnotationTimeCostAttributeSource();
    }

    @Bean
    public TimeCostInterceptor timeCostInterceptor(TimeCostAttributeSource timeCostAttributeSource) {
        TimeCostInterceptor interceptor = new TimeCostInterceptor();
        interceptor.setTimeCostAttributeSource(timeCostAttributeSource);
        return interceptor;
    }
}
