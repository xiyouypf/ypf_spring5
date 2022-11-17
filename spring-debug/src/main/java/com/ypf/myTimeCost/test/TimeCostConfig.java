package com.ypf.myTimeCost;

import com.ypf.myTimeCost.enable.EnableTimeCostManagement;
import com.ypf.myTimeCost.enable.proxyTimeCostManagementConfiguration.BeanFactoryTimeCostAttributeSourceAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableTimeCostManagement
public class TimeCostConfig {
    @Bean
    public BookDao bookDao() {
        return new BookDao();
    }
}
