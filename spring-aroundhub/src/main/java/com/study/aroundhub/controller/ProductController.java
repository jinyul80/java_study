package com.study.aroundhub.controller;

import com.study.aroundhub.data.dto.ProductDto;
import com.study.aroundhub.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product-api")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @GetMapping(value = "/product/{productId}")
    public ProductDto getProduct(@PathVariable String productId) {
        long startTime = System.currentTimeMillis();
        LOGGER.info("perform {} of Around Hub API.", "getProduct");

        ProductDto productDto =productService.getProduct(productId);

        LOGGER.info("Response :: productId = {}, productName = {}, productPrice = {}, productStock = {}, Response Time = {}ms",
                productDto.getProductId(), productDto.getProductName(), productDto.getProductPrice()
                , productDto.getProductStack(), (System.currentTimeMillis() - startTime));

        return productDto;
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
