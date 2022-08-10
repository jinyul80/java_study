package com.study.aroundhub.service;

import com.study.aroundhub.data.dto.ProductDto;

public interface ProductService {

    ProductDto saveProduct(String productId, String productName, int productPrice, int productStack);

    ProductDto getProduct(String productId);
}
