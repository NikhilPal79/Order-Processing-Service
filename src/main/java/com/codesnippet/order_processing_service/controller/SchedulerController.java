package com.codesnippet.order_processing_service.controller;


import com.codesnippet.order_processing_service.dto.CronUpdateRequest;
import com.codesnippet.order_processing_service.repository.SchedulerConfigRepository;
import com.codesnippet.order_processing_service.service.DynamicSchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scheduler")
public class SchedulerController {

    @Autowired
    private SchedulerConfigRepository schedulerConfigRepository;

    @Autowired
    private DynamicSchedulerService dynamicSchedulerService;

    @PostMapping("/update-cron")
    public ResponseEntity<String> updateCron(@RequestBody CronUpdateRequest request){
        dynamicSchedulerService.updateCronExpression(request.getTaskName(), request.getCronExpression());
        return ResponseEntity.ok("Cron expression updated successfully");

    }
}
