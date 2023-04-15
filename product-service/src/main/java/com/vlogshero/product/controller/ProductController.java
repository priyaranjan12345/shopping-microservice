package com.vlogshero.product.controller;

import com.vlogshero.product.dto.ProductRequest;
import com.vlogshero.product.dto.ProductResponse;
import com.vlogshero.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api-v1/product")
public class ProductController {
    private final ProductService productService;

    @Operation(summary = "/createProduct", description = "Create a new product and save to database.")
    @PostMapping("/createProduct")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);
    }

    @Operation(summary = "/getAllProducts", description = "Get or fetch all products from database.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successful"),
                    @ApiResponse(responseCode = "500", description = "Internal server error"),
                    @ApiResponse(responseCode = "1001", description = "Application specific error.")
            }
    )
    @GetMapping("/getAllProducts")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }
}
