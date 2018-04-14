package com.eko.task.service.impl;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.eko.task.domain.TaskDomain;
import com.eko.utils.TaskUtil;

public class TaskCurrentNotRunningFactory implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		TaskDomain taskDomain = (TaskDomain) context.getMergedJobDataMap().get("taskDomain");
		TaskUtil.invokeTaskMethod(taskDomain);
	}

}
