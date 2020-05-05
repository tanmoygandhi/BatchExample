package com.tg.processor.batch;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.tg.processor.model.EmployeeBillingInfo;

public class EmployeeItemReader implements ItemReader<EmployeeBillingInfo>{

	@Override
	public EmployeeBillingInfo read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		// TODO Auto-generated method stub
		return null;
	}

}
