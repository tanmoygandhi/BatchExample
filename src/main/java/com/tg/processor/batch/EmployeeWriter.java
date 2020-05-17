package com.tg.processor.batch;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.List;

import org.beanio.BeanWriter;
import org.beanio.StreamFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.tg.processor.model.EmployeeBillingInfo;

@Component
public class EmployeeWriter implements ItemWriter<EmployeeBillingInfo> {
	
	@Value("${processor.data.output.file.name}")
	String outputFileName;
	
	@Value("${processor.data.output.file.location}")
	String outputFileLocation;

	@Override
	public void write(List<? extends EmployeeBillingInfo> items) throws Exception {

		BeanWriter out = null;
		try {
			// Create writer and write data to file.
			Resource resource = new ClassPathResource("/mapper/employee_billing_info_mapping.xml");
			StreamFactory factory = StreamFactory.newInstance();
			factory.load(resource.getInputStream());
//			File outFile = new File(outputFileLocation + outputFileName);
			File outFile = new File("D:\\app\\target\\contracts\\EmployeeBillingInfo.trg");
//			System.out.println("Output file location::" + outputFileLocation);
//			System.out.println("Output file ::" + outputFileName);
			outFile.createNewFile();
			
			Writer writer = new FileWriter(outFile, true);
			out = factory.createWriter("billinginfos", writer);
			System.out.println("Output file location::" + outFile.getAbsolutePath());
			System.out.println("Output file ::" + outFile.getName());
			if (out != null) {
				for (EmployeeBillingInfo info : items) {
					out.write("employeebillinginfo", info);
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
			
		
		
	}

}
