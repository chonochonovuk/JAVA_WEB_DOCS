package com.exam.springexam.service;

import com.exam.springexam.model.entity.Product;
import com.exam.springexam.model.service.CategoryServiceModel;
import com.exam.springexam.model.service.ProductServiceModel;
import com.exam.springexam.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductServiceModel addProduct(ProductServiceModel productServiceModel) {
        Product product = this.modelMapper.map(productServiceModel,Product.class);
        this.productRepository.saveAndFlush(product);
        return this.modelMapper.map(product,ProductServiceModel.class);
    }

    @Override
    public List<ProductServiceModel> findAllProducts() {
        return this.productRepository.findAll()
                .stream()
                .map(product -> this.modelMapper.map(product,ProductServiceModel.class))
                .collect(Collectors.toList());
    }
}
