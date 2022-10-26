package com.Product.ProductAPI.services;

import com.Product.ProductAPI.model.Product;
import com.Product.ProductAPI.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Optional;

@Service
public class ProductServiceImp {

    @Autowired
    private ProductRepository repository;

    public Object getBySku(final String sku) throws IOException, InterruptedException {
        Optional<Product> productOptional = repository.findById(sku);
        return productOptional.get();
    }

    public Page<Product> getBySkuOrDesignation(String skuOrDesignation, int offset, int pageSize) {
        if ((repository.getBySkuOrDesignation(skuOrDesignation, PageRequest.of(offset,pageSize))).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product Not Found");
        }else{
            return repository.getBySkuOrDesignation(skuOrDesignation,PageRequest.of(offset,pageSize));
        }
    }


    public Product create(Product pt) throws IOException {
        return repository.save(pt);
    }



}
