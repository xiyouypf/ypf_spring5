package com.ypf.myTimeCost;

import org.springframework.context.annotation.AutoProxyRegistrar;
import org.springframework.context.annotation.ImportAware;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.transaction.annotation.ProxyTransactionManagementConfiguration;

import java.util.function.Predicate;

public class TimeCostManagementConfigurationSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{AutoProxyRegistrar.class.getName(),
                ProxyTimeCostManagementConfiguration.class.getName()};
    }

    @Override
    public Predicate<String> getExclusionFilter() {
        return ImportSelector.super.getExclusionFilter();
    }
}
