package com.tg.processor.batch;

import org.springframework.batch.item.ItemProcessor;

import com.tg.processor.model.EmployeeBillingInfo;

public class EmployeeProcessor implements ItemProcessor<EmployeeBillingInfo, EmployeeBillingInfo> {

	@Override
	public EmployeeBillingInfo process(EmployeeBillingInfo item) throws Exception {
//		System.out.println("Inside of EmployeeProcessor::process()");
//		if (item.getName() != null && !item.getName().isEmpty()) {
//			item.setName(item.getName().toUpperCase());
//		}
//		System.out.println("Exiting from EmployeeProcessor::process()");
		return item;
	}

}
