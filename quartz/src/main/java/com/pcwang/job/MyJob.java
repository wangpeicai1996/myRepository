package com.pcwang.job;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * job类
 * @author Administrator
 *
 */
public class MyJob implements Job{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("定时任务触发"+new Date());
	}

}
