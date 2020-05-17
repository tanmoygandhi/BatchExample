package com.tg.processor.batch;

import java.io.File;
import java.util.Date;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Value;

public class EmployeeBillHrJobListener implements JobExecutionListener{
	
	private static final String LOG_MESSAGE_MILLISECONDS = "Total batch job execution time in milliseconds = ";

	@Value("${processor.data.output.file.name}")
	String outputFileName;
	
	@Value("${processor.data.output.file.location}")
	String outputFileLocation;
	
	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("Inside EmployeeBillHrJobListener:: beforeJob()");
		try {
			// Delete any existing target file.
			File outFile = new File("D:\\app\\target\\contracts\\EmployeeBillingInfo.trg");
			if (outFile.exists()) {
				boolean delInd = outFile.delete();
				System.out.println("Existing output file detected. Deleting.....");
				if (delInd) {
					System.out.println("Existing output file deleted successfully.");
				}
			} else {
				System.out.println("No existing file detected. Nothing to delete.");
			}
		} catch(Exception e) {
			System.out.println("Exception while deleting existing file....");
			System.out.println(e.getMessage());
		}
		System.out.println("Exiting from EmployeeBillHrJobListener:: beforeJob()");
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		
		System.out.println("Inside EmployeeBillHrJobListener:: afterJob()");
		// get job creation time.
		Date start = jobExecution.getCreateTime();
		// get job end time.
		Date end = jobExecution.getEndTime();
		// calculate the job execution time in milliseconds.
		long diff = end.getTime() - start.getTime();
		
		System.out.println(LOG_MESSAGE_MILLISECONDS + diff);
		
		System.out.println("Exiting from EmployeeBillHrJobListener:: afterJob()");
	}

}
