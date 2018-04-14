package com.eko.task.service.impl;

import com.eko.dynamicdb.SwitchDatasource;
import com.eko.task.service.TaskService;

public class TaskServiceImpl implements TaskService {
	public TaskServiceImpl(){}
	@Override
	@SwitchDatasource("onedbDatasource")
	public void printALine() {
		System.out.println("line");
	}

}
