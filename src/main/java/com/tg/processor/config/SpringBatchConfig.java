package com.tg.processor.config;

import java.io.File;

import org.beanio.BeanReader;
import org.beanio.BeanWriter;
import org.beanio.InvalidRecordException;
import org.beanio.RecordContext;
import org.beanio.StreamFactory;
import org.beanio.spring.BeanIOFlatFileItemReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import com.tg.processor.batch.EmployeeBillHrJobListener;
import com.tg.processor.batch.EmployeeProcessor;
import com.tg.processor.batch.EmployeeWriter;
import com.tg.processor.model.EmployeeBillingInfo;

@EnableBatchProcessing
@Component
public class SpringBatchConfig {
	
	@Value("${processor.data.input.file.name}")
	String inFileName;
	
	@Value("${processor.data.input.file.location}")
	String inFileLocation;
	
	@Autowired
	JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	StepBuilderFactory stepBuilderFactory;
		
	@Bean	
	public Step step() {
		return stepBuilderFactory.get("orderStep1").<EmployeeBillingInfo, EmployeeBillingInfo> chunk(10)
				.reader(reader()).processor(processor())
				.writer(writer()).build();
	}
	
	
	@Bean(name="empBillHours")
	public Job loadJob() {
		return jobBuilderFactory.get("empBillHours")
				.incrementer(new RunIdIncrementer()).listener(listener())
				.flow(step()).end().build();
	}
	
	@Bean
	public JobExecutionListener listener() {
		return new EmployeeBillHrJobListener();
	}
	
	@Bean
	public ItemWriter<EmployeeBillingInfo> writer() {
		return new EmployeeWriter();
	}
	
	@Bean
	public ItemProcessor<EmployeeBillingInfo, EmployeeBillingInfo> processor() {
		return new EmployeeProcessor();
	}
	
	@Bean
	public BeanIOFlatFileItemReader<EmployeeBillingInfo> reader() {

		System.out.println("Inside SpringBatchConfig:: reader()");
		BeanIOFlatFileItemReader<EmployeeBillingInfo> reader = null;
		try {
			// create the reader
			reader = new BeanIOFlatFileItemReader<EmployeeBillingInfo>();
			reader.setStreamFactory(StreamFactory.newInstance());
			reader.setResource(new FileSystemResource(inFileLocation + inFileName));
			reader.setStreamMapping(new ClassPathResource("/mapper/employee_billing_info_mapping.xml"));
			reader.setStreamName("billinginfos");
		
			reader.afterPropertiesSet();
			reader.open(new ExecutionContext());
		}catch (InvalidRecordException ex) {
		        RecordContext context = ex.getRecordContext();
		        if (context.hasRecordErrors()) {
		            for (String error : context.getRecordErrors()) {
		               System.out.println(error);
		            }
		        }
		        if (context.hasFieldErrors()) {
		            for (String field : context.getFieldErrors().keySet()) {
		                for (String error : context.getFieldErrors(field)) {
		                	 System.out.println(error);
		                }
		            }
		        }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Exiting from SpringBatchConfig:: reader()");
		return reader;
	}

}
