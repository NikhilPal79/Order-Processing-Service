package com.codesnippet.order_processing_service.controller;

import com.codesnippet.order_processing_service.entity.ScheduleConfig;
import com.codesnippet.order_processing_service.repository.ScheduleConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    
    @Autowired
    private ScheduleConfigRepository scheduleConfigRepository;

    @PostMapping("/update")
    public ResponseEntity<String> updateSchedule(@RequestParam String cronExpression) {
        ScheduleConfig config = scheduleConfigRepository.findByTaskName("processOrders")
                .orElse(new ScheduleConfig());
        
        config.setTaskName("processOrders");
        config.setCronExpression(cronExpression);
        scheduleConfigRepository.save(config);

        return ResponseEntity.ok("Cron expression updated successfully!");
    }
}
