/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htc.RealtimeAuctionSystem.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Admin
 */
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class BidRequest {
    private Long itemId;
    private Long userId;
    private BigDecimal bidAmount;
}
