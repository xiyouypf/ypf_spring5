package com.ypf.myTimeCost.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(TimeCostConfig.class);
        BookDao bookDao = context.getBean(BookDao.class);
        bookDao.getPrice();
        System.out.println();
        bookDao.getNumber();
    }
}
