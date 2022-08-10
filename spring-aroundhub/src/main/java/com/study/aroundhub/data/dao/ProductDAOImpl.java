package com.study.aroundhub.data.dao;

import com.study.aroundhub.data.entity.ProductEntity;
import com.study.aroundhub.data.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductDAOImpl implements ProductDAO{

    private final ProductRepository productRepository;

    @Override
    public ProductEntity saveProduct(ProductEntity productEntity) {
        productRepository.save(productEntity);
        return productEntity;
    }

    @Override
    public ProductEntity getProduct(String productId) {
        Optional<ProductEntity> productEntity = productRepository.findById(productId);

        return productEntity.orElse(null);
    }
}
