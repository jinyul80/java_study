package com.study.aroundhub.controller;

import com.study.aroundhub.data.dto.ProductDto;
import com.study.aroundhub.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) {

        var response = productService.saveProduct(productDto.getProductId(), productDto.getProductName(), productDto.getProductPrice(), productDto.getProductStack());

        LOGGER.info("Response :: productId = {}, productName = {}, productPrice = {}, productStock = {}",
                response.getProductId(), response.getProductName(), response.getProductPrice()
                , response.getProductStack());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(value = "/product/{productId}")
    public ProductDto deleteProduct(@PathVariable String productId) {
        return null;
    }

}
