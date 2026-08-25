package com.htc.RealtimeAuctionSystem.service;


import com.htc.RealtimeAuctionSystem.dto.ItemsDto;
import com.htc.RealtimeAuctionSystem.pojo.Items;
import com.htc.RealtimeAuctionSystem.repository.ItemRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemsRepository;

    public Optional<ItemsDto> getItemById(Long id) {
        return itemsRepository.findById(id)
                .map(item -> ItemsDto.builder()
                        .id(item.getId())
                        .name(item.getName())
                        .startPrice(item.getStartPrice())
                        .currentPrice(item.getCurrentPrice())
                        .status(item.getStatus())
                        .build()
                );
    }
}
