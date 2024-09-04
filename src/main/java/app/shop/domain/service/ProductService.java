package app.shop.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import app.shop.domain.entity.Product;
import app.shop.domain.repository.ProductRepository;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }
}
