package com.study.aroundhub.controller;

import com.study.aroundhub.data.dto.ProductDto;
import com.study.aroundhub.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product-api")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping(value = "/product/{productId}")
    public ProductDto getProduct(@PathVariable String productId) {
        return productService.getProduct(productId);
    }

    @PostMapping(value = "/product")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return productService.saveProduct(productDto.getProductId(), productDto.getProductName(), productDto.getProductPrice(), productDto.getProductStack());
    }

    @DeleteMapping(value = "/product/{productId}")
    public ProductDto deleteProduct(@PathVariable String productId) {
        return null;
    }

}
