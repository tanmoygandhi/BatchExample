package com.tg.processor.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.tg.processor.model.EmployeeBillingInfo;

public class EmployeeWriter implements ItemWriter<EmployeeBillingInfo> {

	@Override
	public void write(List<? extends EmployeeBillingInfo> items) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
