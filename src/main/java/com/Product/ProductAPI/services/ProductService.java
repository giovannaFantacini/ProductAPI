package com.Product.ProductAPI.services;

import com.Product.ProductAPI.model.Product;
import org.springframework.data.domain.Page;

import java.io.IOException;

public interface ProductService {

    Object getBySku(String sku) throws IOException, InterruptedException;

    Page<Product> getBySkuOrDesignation (String skuOrDesignation, int offset, int pageSize);

    Product create(Product pt) throws IOException;

}
