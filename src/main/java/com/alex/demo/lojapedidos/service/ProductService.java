package com.alex.demo.lojapedidos.service;

import com.alex.demo.lojapedidos.dto.ProductDTO;
import com.alex.demo.lojapedidos.exceptions.ResourceNotFoundException;
import com.alex.demo.lojapedidos.model.Product;
import com.alex.demo.lojapedidos.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        var product = mapToEntity(productDTO);
        repository.save(product);
        return mapToDTO(product);
    }

    public ProductDTO getProductById(Long id) {
        var product = repository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Product", "id", id));
        return mapToDTO(product);
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> products = repository.findAll();

        return products.stream().map(product -> mapToDTO(product)).toList();
    }

    public ProductDTO updateProduct(Long productId, ProductDTO productDTO) {
        var product = repository.findById(productId).orElseThrow( () -> new ResourceNotFoundException("Product", "id", productId));

        product.setProductId(productId);
        product.setName(productDTO.name());
        product.setDescription(productDTO.description());
        product.setPrice(productDTO.price());
        Product savedProduct = repository.save(product);

        return mapToDTO(savedProduct);
    }

    public void deleteProduct(Long productId) {
        Product product = repository.findById(productId).orElseThrow(() ->
                new ResourceNotFoundException("Product", "id", productId));
        repository.delete(product);
    }


    private Product mapToEntity(ProductDTO dto) {
        var product = new Product();
        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setPrice(dto.price());
        return product;
    }
    private ProductDTO mapToDTO(Product product) {
        return new ProductDTO(product.getName(), product.getDescription(), product.getPrice());
    }
}
