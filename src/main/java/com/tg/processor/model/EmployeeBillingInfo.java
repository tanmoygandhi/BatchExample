package com.tg.processor.model;

public class EmployeeBillingInfo {
	
	private String empId;
	private String name;
	private String projectId;
	private String wklybillableHour;
	private String totalBillableHour;
	
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getWklybillableHour() {
		return wklybillableHour;
	}
	public void setWklybillableHour(String wklybillableHour) {
		this.wklybillableHour = wklybillableHour;
	}
	public String getTotalBillableHour() {
		return totalBillableHour;
	}
	public void setTotalBillableHour(String totalBillableHour) {
		this.totalBillableHour = totalBillableHour;
	}
	@Override
	public String toString() {
		return "EmployeeBillingInfo [empId=" + empId + ", name=" + name + ", projectId=" + projectId
				+ ", wklybillableHour=" + wklybillableHour + ", totalBillableHour=" + totalBillableHour + "]";
	}
	
}
