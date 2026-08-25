/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htc.RealtimeAuctionSystem.producer;

import com.htc.RealtimeAuctionSystem.config.RabbitMQConfig;
import com.htc.RealtimeAuctionSystem.dto.BidRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
@RequiredArgsConstructor
public class BidProducer {
    private final RabbitTemplate rabbitTemplate;
    
    public void sendBid(BidRequest bidRequest) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, bidRequest);
        System.out.println("Đã đẩy bid vào queue: " + bidRequest);
    }
}
