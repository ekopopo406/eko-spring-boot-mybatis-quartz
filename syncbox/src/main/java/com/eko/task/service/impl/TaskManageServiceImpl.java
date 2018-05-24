package com.eko.task.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eko.task.domain.TaskDomain;
import com.eko.task.service.TaskManageService;
import com.eko.task.service.TaskRecordService;

@Service("taskManageService")
public class TaskManageServiceImpl implements TaskManageService{
	
	@Resource(name = "scheduler")
	private Scheduler scheduler;

	@Autowired
	private TaskRecordService taskRecordService;
	@Override
	public void addTaskJob(TaskDomain taskDomain) {
		try {

			TriggerKey trikey = TriggerKey.triggerKey(taskDomain.getJobName(), taskDomain.getJobGroupName());
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(trikey);
			if(trigger==null){
				Class<TaskClassInvokeFactory> currentClass = TaskClassInvokeFactory.class;
				JobDetail theJobDetial = JobBuilder.newJob(currentClass).withIdentity(taskDomain.getJobName(), taskDomain.getJobGroupName()).build();
				theJobDetial.getJobDataMap().put("taskDomain", taskDomain);
				CronScheduleBuilder schedBuilder = CronScheduleBuilder.cronSchedule(taskDomain.getCronExpression()) ;
				trigger = TriggerBuilder.newTrigger().withIdentity(taskDomain.getJobName(), taskDomain.getJobGroupName()).withSchedule(schedBuilder).build();
				scheduler.scheduleJob(theJobDetial, trigger);
			}else{
				CronScheduleBuilder schedBuilder = CronScheduleBuilder.cronSchedule(trigger.getCronExpression()) ;
				trigger = trigger.getTriggerBuilder().withIdentity(trikey).withSchedule(schedBuilder).build();
				scheduler.rescheduleJob(trikey, trigger);
			}
			//taskRecordService.createNewTask(taskDomain);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void pauseTaskJob(TaskDomain taskDomain) {
		try{
			JobKey jobkey = JobKey.jobKey(taskDomain.getJobName(), taskDomain.getJobGroupName());
			scheduler.pauseJob(jobkey);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public void resumeTaskJob(TaskDomain taskDomain) {
		try{
			JobKey jobkey = JobKey.jobKey(taskDomain.getJobName(), taskDomain.getJobGroupName());
			scheduler.resumeJob(jobkey);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteTaskJob(TaskDomain taskDomain) {
		try{
			JobKey jobkey = JobKey.jobKey(taskDomain.getJobName(), taskDomain.getJobGroupName());
			scheduler.deleteJob(jobkey);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}


	@Override
	public void updateTaskJob(TaskDomain taskDomain) {
		try{
			TriggerKey trikey = TriggerKey.triggerKey(taskDomain.getJobName(), taskDomain.getJobGroupName());
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(trikey);
			CronScheduleBuilder schedBuilder = CronScheduleBuilder.cronSchedule(taskDomain.getCronExpression()) ;
			trigger = trigger.getTriggerBuilder().withIdentity(trikey).withSchedule(schedBuilder).build();
			scheduler.rescheduleJob(trikey, trigger);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<TaskDomain> getAllStartedJobs() {
		 try {  
	           
	            List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();  
	            List<TaskDomain> jobList = new ArrayList<TaskDomain>(executingJobs.size());  
	            for (JobExecutionContext executingJob : executingJobs) {  
	            	TaskDomain job = new TaskDomain();  
	                JobDetail jobDetail = executingJob.getJobDetail();  
	                JobKey jobKey = jobDetail.getKey();  
	                Trigger trigger = executingJob.getTrigger();  
	                job.setJobName(jobKey.getName());  
	                job.setJobGroupName(jobKey.getGroup());  
	                job.setJobDescription(trigger.getKey().toString());  
	                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());  
	                job.setIsStart(triggerState.name());  
	                if (trigger instanceof CronTrigger) {  
	                    CronTrigger cronTrigger = (CronTrigger) trigger;  
	                    String cronExpression = cronTrigger.getCronExpression();  
	                    job.setCronExpression(cronExpression);  
	                }  
	                jobList.add(job);  
	            }  
	            return jobList;  
	        } catch (Exception e) {  
	        	throw new RuntimeException(e);  
	        }  
	}

	@Override
	public List<TaskDomain> getAllJobs() {
		try {  
             
            GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();  
            Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);  
            List<TaskDomain> jobList = new ArrayList<TaskDomain>();  
            for (JobKey jobKey : jobKeys) {  
                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);  
                for (Trigger trigger : triggers) {  
                	TaskDomain taskDomain = new TaskDomain();  
                	taskDomain.setJobName(jobKey.getName());  
                	taskDomain.setJobGroupName(jobKey.getGroup());  
                	taskDomain.setJobDescription(trigger.getKey().toString());  
                    Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());  
                    taskDomain.setIsEffect(triggerState.name());  
                    taskDomain.setIsStart(triggerState.name());
                    if (trigger instanceof CronTrigger) {  
                        CronTrigger cronTrigger = (CronTrigger) trigger;  
                        String cronExpression = cronTrigger.getCronExpression();  
                        taskDomain.setCronExpression(cronExpression);  
                    }  
                    jobList.add(taskDomain);  
                }  
            }  
            return jobList;  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
	}

}
