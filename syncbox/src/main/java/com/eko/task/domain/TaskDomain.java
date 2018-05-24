package com.eko.task.domain;

import java.io.Serializable;

public class TaskDomain implements Serializable{

	private static final long serialVersionUID = 2061975721442853581L;
 
	private String id;
    private String cronExpression;
    /**0 no effect,1 is effect*/
    private String isEffect;
    /**0 is stop,1 is running*/
    private String isStart;
    private String jobName;
    private String jobGroupName;
    private String classAbsName;
	private String methodName;
	private String jobDescription;
	private String springBeanName;
	
	public String getSpringBeanName() {
		return springBeanName;
	}
	public void setSpringBeanName(String springBeanName) {
		this.springBeanName = springBeanName;
	}
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	public String getJobGroupName() {
		return jobGroupName;
	}
	public void setJobGroupName(String jobGroupName) {
		this.jobGroupName = jobGroupName;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getClassAbsName() {
		return classAbsName;
	}
	public void setClassAbsName(String classAbsName) {
		this.classAbsName = classAbsName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCronExpression() {
		return cronExpression;
	}
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
	public String getIsEffect() {
		return isEffect;
	}
	public void setIsEffect(String isEffect) {
		this.isEffect = isEffect;
	}
	public String getIsStart() {
		return isStart;
	}
	public void setIsStart(String isStart) {
		this.isStart = isStart;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

    
    
}
