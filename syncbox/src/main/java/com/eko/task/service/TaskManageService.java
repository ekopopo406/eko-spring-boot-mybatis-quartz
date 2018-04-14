package com.eko.task.service;

import java.util.List;

import com.eko.task.domain.TaskDomain;

public interface TaskManageService {
	public void addTaskJob(TaskDomain taskDomain);
	public void pauseTaskJob(TaskDomain taskDomain);
	public void resumeTaskJob(TaskDomain taskDomain);
	public void deleteTaskJob(TaskDomain taskDomain);
//	public void startTaskJob(TaskDomain taskDomain);
	public void updateTaskJob(TaskDomain taskDomain);
	public List<TaskDomain> getAllStartedJobs();
	public List<TaskDomain> getAllJobs();
}
