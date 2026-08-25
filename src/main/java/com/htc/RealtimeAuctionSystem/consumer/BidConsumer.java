/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htc.RealtimeAuctionSystem.consumer;

import com.htc.RealtimeAuctionSystem.config.RabbitMQConfig;
import com.htc.RealtimeAuctionSystem.dto.BidRequest;
import com.htc.RealtimeAuctionSystem.pojo.Bids;
import com.htc.RealtimeAuctionSystem.pojo.Items;
import com.htc.RealtimeAuctionSystem.pojo.Users;
import com.htc.RealtimeAuctionSystem.repository.BidsRepository;
import com.htc.RealtimeAuctionSystem.repository.ItemRepository;
import com.htc.RealtimeAuctionSystem.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.time.Instant;
import java.util.Date;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
@RequiredArgsConstructor
public class BidConsumer {

    private final ItemRepository itemsRepository;
    private final BidsRepository bidsRepository;
    private final UserRepository userRepository;

    @RabbitListener(queues = RabbitMQConfig.BID_QUEUE)
    @Transactional
    public void receiveBid(BidRequest bidRequest) {
        Items item = itemsRepository.findById(bidRequest.getItemId()).orElseThrow();
        Users user = userRepository.findById(bidRequest.getUserId()).orElseThrow();
        if (bidRequest.getBidAmount().compareTo(item.getCurrentPrice()) > 0) {
            item.setCurrentPrice(bidRequest.getBidAmount());
            this.itemsRepository.save(item);

            Bids bid = new Bids();
            bid.setItemId(item);
            bid.setUserId(user);
            bid.setBidAmount(bidRequest.getBidAmount());
            bid.setCreatedAt(Date.from(Instant.now()));
            this.bidsRepository.save(bid);

            System.out.println("Xử lý thành công giá mới: " + bidRequest.getBidAmount());
        } else {
            System.out.println("Giá đặt không hợp lệ.");
        }
    }
}
