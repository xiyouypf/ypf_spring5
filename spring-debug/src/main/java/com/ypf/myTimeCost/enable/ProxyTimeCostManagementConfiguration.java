package com.ypf.myTimeCost.enable;

import com.ypf.myTimeCost.enable.proxyTimeCostManagementConfiguration.BeanFactoryTimeCostAttributeSourceAdvisor;
import com.ypf.myTimeCost.enable.proxyTimeCostManagementConfiguration.TimeCostInterceptor;
import com.ypf.myTimeCost.attributeSource.AnnotationTimeCostAttributeSource;
import com.ypf.myTimeCost.attributeSource.TimeCostAttributeSource;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

@Configuration(proxyBeanMethods = false)
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class ProxyTimeCostManagementConfiguration {

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public BeanFactoryTimeCostAttributeSourceAdvisor timeCostAdvisor(
            TimeCostAttributeSource timeCostAttributeSource,
            TimeCostInterceptor timeCostInterceptor) {
        BeanFactoryTimeCostAttributeSourceAdvisor advisor = new BeanFactoryTimeCostAttributeSourceAdvisor();
        advisor.setTimeCostAttributeSource(timeCostAttributeSource);
        advisor.setAdvice(timeCostInterceptor);
        return advisor;
    }

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public TimeCostAttributeSource timeCostAttributeSource() {
        return new AnnotationTimeCostAttributeSource();
    }

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public TimeCostInterceptor timeCostInterceptor(TimeCostAttributeSource timeCostAttributeSource) {
        TimeCostInterceptor interceptor = new TimeCostInterceptor();
        interceptor.setTimeCostAttributeSource(timeCostAttributeSource);
        return interceptor;
    }
}
