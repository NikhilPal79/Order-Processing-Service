package com.codesnippet.order_processing_service.schedulers;

import com.codesnippet.order_processing_service.entity.Order;
import com.codesnippet.order_processing_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderScheduler {
    @Autowired
    OrderRepository orderRepository;

    @Scheduled(fixedRate = 1000)
    public void processPendingOrders() {
        System.out.println("Processing Orders");
        List<Order> orders = orderRepository.findByStatus("PENDING");
        orders.forEach(order -> {
            order.setStatus("COMPLETED");
            System.out.println(order.getCustomerEmail());
            orderRepository.save(order);
        });
        System.out.println("Processed pending orders"+ orders.size());
    }
}
