package com.pcwang.job;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 测试运行类
 * @author Administrator
 *
 */
public class Main {

	public static void main(String[] args) throws SchedulerException {
		//1.创建Job对象，你要做什么事？
		JobDetail job = JobBuilder.newJob(MyJob.class).build();
		//2.创建Trigger对象，在什么时候做？
		/**
		 * 简单的tirgger触发时间：通过Quartz提供的一些方法来完成简单的重复调用
		 * cron表达式触发时间：按照cron表达式给定触发时间
		 */
		//简单trigger
		Trigger trigger1 = TriggerBuilder.newTrigger().withSchedule(SimpleScheduleBuilder.repeatSecondlyForever()).build();
		//cron表达式trigger
		Trigger trigger2 = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?")).build();

		//3.创建Scheduler对象，什么时候做什么事?
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		scheduler.scheduleJob(job,trigger2);
		//启动任务
		scheduler.start();
	}
}
