/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htc.RealtimeAuctionSystem.controller;

import com.htc.RealtimeAuctionSystem.dto.BidRequest;
import com.htc.RealtimeAuctionSystem.producer.BidProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api/bids")
@RequiredArgsConstructor
public class BidController {
    private final BidProducer bidProducer;
    
    @PostMapping
    public ResponseEntity<String> placeBid(@RequestBody BidRequest bidRequest){
        this.bidProducer.sendBid(bidRequest);
        return ResponseEntity.ok("Request đặt giá đã được đưa vào hàng đợi.");
    }
}
