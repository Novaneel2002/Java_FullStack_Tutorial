package com.ems.EMS.model;
 
import java.util.List;
 
public class Performance {
	int employee;
	double rating;
	String feedback;
	List<String> projectsHandled;
	boolean eligibleForpromotion;
	public Performance(int employee, double rating, String feedback, List<String> projectsHandled,
			boolean eligibleForpromotion) {
		super();
		this.employee = employee;
		this.rating = rating;
		this.feedback = feedback;
		this.projectsHandled = projectsHandled;
		this.eligibleForpromotion = eligibleForpromotion;
	}
	public Performance() {
    }
	public int getEmployee() {
		return employee;
	}
	public void setEmployee(int employee) {
		this.employee = employee;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public List<String> getProjectsHandled() {
		return projectsHandled;
	}
	public void setProjectsHandled(List<String> projectsHandled) {
		this.projectsHandled = projectsHandled;
	}
	public boolean isEligibleForpromotion() {
		return eligibleForpromotion;
	}
	public void setEligibleForpromotion(boolean eligibleForpromotion) {
		this.eligibleForpromotion = eligibleForpromotion;
	}
	@Override
	public String toString() {
		return "Performance [employee=" + employee + ", rating=" + rating + ", feedback=" + feedback
				+ ", projectsHandled=" + projectsHandled + ", eligibleForpromotion=" + eligibleForpromotion + "]";
	}

 
}