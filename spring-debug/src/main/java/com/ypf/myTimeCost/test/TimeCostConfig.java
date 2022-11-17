package com.ypf.myTimeCost.test;

import com.ypf.myTimeCost.enable.EnableTimeCostManagement;
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
