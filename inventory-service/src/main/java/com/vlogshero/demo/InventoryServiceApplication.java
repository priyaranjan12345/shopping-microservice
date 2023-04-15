package com.vlogshero.demo;

import com.vlogshero.demo.model.Inventory;
import com.vlogshero.demo.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication

public class InventoryServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
//		return args -> {
//			Inventory inventory1 = new Inventory();
//			inventory1.setSkuCode("iPhoneX");
//			inventory1.setQty(10);
//
//			Inventory inventory2 = new Inventory();
//			inventory2.setSkuCode("iPhone11");
//			inventory2.setQty(8);
//
//			inventoryRepository.save(inventory1);
//			inventoryRepository.save(inventory2);
//		};
//	}
}
