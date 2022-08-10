package com.study.aroundhub.service;

import com.study.aroundhub.data.dao.ProductDAO;
import com.study.aroundhub.data.dto.ProductDto;
import com.study.aroundhub.data.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductDAO productDAO;

    @Override
    public ProductDto saveProduct(String productId, String productName, int productPrice, int productStack) {

        ProductEntity tempEntity = new ProductEntity(productId, productName, productPrice, productStack);
        ProductEntity productEntity = productDAO.saveProduct(tempEntity);

        return new ProductDto(productEntity.getProductId(),
                productEntity.getProductName(), productEntity.getProductPrice(), productEntity.getProductStack());
    }

    @Override
    public ProductDto getProduct(String productId) {

        ProductEntity productEntity = productDAO.getProduct(productId);

        return new ProductDto(productEntity.getProductId(),
                productEntity.getProductName(), productEntity.getProductPrice(), productEntity.getProductStack());
    }
}

