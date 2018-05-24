package com.eko.task.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eko.mapper.task.TaskMapper;
import com.eko.task.domain.TaskDomain;
import com.eko.task.service.TaskRecordService;
@Service("taskRecordService")
public class TaskRecordServiceImpl implements TaskRecordService {
	@Autowired
	private TaskMapper taskMapper;
	
	@Override
	public int createNewTask(TaskDomain taskDomain) {
		return taskMapper.insertNewTask(taskDomain);
	}

}
