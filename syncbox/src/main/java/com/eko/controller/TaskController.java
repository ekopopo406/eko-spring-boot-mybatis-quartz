package com.eko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.eko.task.domain.TaskDomain;
import com.eko.task.service.TaskManageService;
import com.eko.utils.StringPool;
import com.eko.utils.TimeUtil;

@RestController("/Task")
public class TaskController {
	private static String JOB_GROUP_NAME = "JOB_GROUP_NAME";
	private static String TRIGGER_GROUP_NAME = "TRIGGER_GROUP_NAME";
	@Autowired
	private TaskManageService taskManageService;
	@RequestMapping(value = "/starttask", method = RequestMethod.GET)
	@ResponseBody
	public void startTask(
			@RequestParam(value = "targetCron", required = false) String targetCron,
			@RequestParam(value = "targetTask", required = false) String targetTask){
		String jobName = TimeUtil.getAllShortCurrDateTime();
		TaskDomain taskDomain = new TaskDomain();
		taskDomain.setClassAbsName("com.eko.task.service.impl.TaskServiceImpl");
		taskDomain.setCronExpression(targetCron);
		taskDomain.setJobName(jobName);
		taskDomain.setJobGroupName(JOB_GROUP_NAME);
		taskDomain.setMethodName("printALine");
		taskDomain.setIsEffect(StringPool.NUMBER_TRUE);
		taskDomain.setIsStart(StringPool.NUMBER_FALSE);
		System.out.println(TimeUtil.getAllShortCurrDateTime());
		taskManageService.addTaskJob(taskDomain);
	}
 
}
