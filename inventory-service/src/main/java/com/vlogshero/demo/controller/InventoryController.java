package com.vlogshero.demo.controller;

import com.vlogshero.demo.dto.InventoryResponse;
import com.vlogshero.demo.service.InventoryService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api-v1/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

//    @GetMapping("/skuCodeAvailability/{skuCode}")
//    @ResponseStatus(HttpStatus.OK)
//    public boolean skuCodeAvailability(@PathVariable("skuCode") String skuCode) {
//        return inventoryService.isSkuCodePresent(skuCode);
//    }

    @GetMapping("/skuCodesAvailability")
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> skuCodesAvailability(@RequestParam List<String> skuCodes) {
        return inventoryService.isSkuCodesPresent(skuCodes);
    }
}
