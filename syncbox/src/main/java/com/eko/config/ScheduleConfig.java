package com.eko.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class ScheduleConfig {
	
    @Bean(name = "scheduler")  
    public SchedulerFactoryBean schedulerFactory() {  
        SchedulerFactoryBean bean = new SchedulerFactoryBean();   
        bean.setOverwriteExistingJobs(true);   
        return bean;  
    }  
}
