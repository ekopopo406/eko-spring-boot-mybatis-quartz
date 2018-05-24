package com.eko.task.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eko.dynamicdb.SwitchDatasource;
import com.eko.mapper.task.TaskMapper;
import com.eko.task.domain.TaskDomain;
import com.eko.task.service.TaskService;
@Service("taskService")
public class TaskServiceImpl implements TaskService {

	public TaskServiceImpl(){}
	@Override
	@SwitchDatasource("onedbDatasource")
	public void printALine() {
		System.out.println("line"+Thread.currentThread().getName());
	}

}
