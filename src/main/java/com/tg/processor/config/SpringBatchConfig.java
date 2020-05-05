package com.tg.processor.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.tg.processor.batch.EmployeeBillHrJobListener;
import com.tg.processor.batch.EmployeeItemReader;
import com.tg.processor.batch.EmployeeProcessor;
import com.tg.processor.batch.EmployeeWriter;
import com.tg.processor.model.EmployeeBillingInfo;

@EnableBatchProcessing
@Component
public class SpringBatchConfig {
	
	@Autowired
	JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	StepBuilderFactory stepBuilderFactory;
	
//	@Bean(name="empBillHours")
//	public Job processJob {
//		return jobBuilderFactory.get("empBillHours")
//				.incrementer(new RunIdIncrementer()).listener(listener())
//				.flow(step()).end().build();
//		
//	}
		
	@Bean	
	public Step step() {
		return stepBuilderFactory.get("orderStep1").<EmployeeBillingInfo, EmployeeBillingInfo> chunk(10)
				.reader( new EmployeeItemReader()).processor(new EmployeeProcessor())
				.writer(new EmployeeWriter()).build();
	}
	
	@Bean
	public JobExecutionListener listener() {
		return new EmployeeBillHrJobListener();
	}
	
	@Bean(name="empBillHours")
	public Job loadJob() {
		return jobBuilderFactory.get("empBillHours")
				.incrementer(new RunIdIncrementer()).listener(listener())
				.flow(step()).end().build();
	}
	
//	@Bean
//	public JobLauncher getJobLauncher() throws Exception {
//		SimpleJobLauncher simpleJobLauncher = new SimpleJobLauncher();
//		simpleJobLauncher.afterPropertiesSet();
//		return simpleJobLauncher;
//		
//	}

}
