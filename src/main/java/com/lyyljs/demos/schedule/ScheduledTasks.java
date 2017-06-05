package com.lyyljs.demos.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lyyljs.demos.common.logger.MethodDescription;

@Component
public class ScheduledTasks {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	@Scheduled(cron="1-10 * * * * ?")
	@MethodDescription(description="定时任务测试")
	public void reportCurrentTime(){
		logger.info("现在时间：" + dateFormat.format(new Date()));
	}
	
}
