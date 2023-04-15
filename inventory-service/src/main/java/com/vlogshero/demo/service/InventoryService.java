package com.vlogshero.demo.service;

import com.vlogshero.demo.dto.InventoryResponse;
import com.vlogshero.demo.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

//    @Transactional(readOnly = true)
//    public boolean isSkuCodePresent(String skuCode) {
//        return inventoryRepository.findBySkuCode(skuCode).isPresent();
//    }

    @Transactional(readOnly = true)
    @SneakyThrows
    public List<InventoryResponse> isSkuCodesPresent(List<String> skuCodes) {
        return inventoryRepository.findBySkuCodeIn(skuCodes).stream().map(inventory ->
                InventoryResponse.builder()
                        .skuCode(inventory.getSkuCode())
                        .isPresent(inventory.getQty() > 0)
                        .build()
        ).toList();
    }
}
