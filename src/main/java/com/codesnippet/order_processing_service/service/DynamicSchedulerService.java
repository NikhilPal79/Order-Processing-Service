package com.codesnippet.order_processing_service.service;

import com.codesnippet.order_processing_service.entity.ScheduleConfig;
import com.codesnippet.order_processing_service.repository.SchedulerConfigRepository;
import com.codesnippet.order_processing_service.schedulers.OrderScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.SimpleAsyncTaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.concurrent.ScheduledFuture;

@Service
public class DynamicSchedulerService {

    @Autowired
    private SchedulerConfigRepository  schedulerConfigRepository;

    @Autowired
    private OrderScheduler orderScheduler;

    private final ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
    private ScheduledFuture<?> scheduledTask;


    private DynamicSchedulerService(){
        taskScheduler.initialize();
    }

    public void updateCronExpression(String taskName, String newCron) {
        ScheduleConfig config = schedulerConfigRepository.findByTaskName(taskName)
                .orElse(new ScheduleConfig());
        config.setTaskName(taskName);
        config.setCronExpression(newCron);
        schedulerConfigRepository.save(config);

        restartScheduledTask(newCron);
    }

    public void restartScheduledTask(String cronExpression) {
        if (scheduledTask != null) {
            scheduledTask.cancel(false);
        }
        scheduledTask = taskScheduler.schedule(orderScheduler::processPendingOrders, new CronTrigger(cronExpression));
    }
}
