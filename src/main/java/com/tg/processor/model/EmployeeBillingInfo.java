package com.tg.processor.model;

public class EmployeeBillingInfo {
	
	private String empId;
	private String projectId;
	private String projectName;
	private String wklybillableHour;
	private String totalBillableHour;
	private String location;
	
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
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
	
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "EmployeeBillingInfo [empId=" + empId + ", projectId=" + projectId + ", projectName="
				+ projectName + ", wklybillableHour=" + wklybillableHour + ", totalBillableHour=" + totalBillableHour
				+ "]";
	}
}
