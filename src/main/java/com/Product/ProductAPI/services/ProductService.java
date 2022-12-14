package com.Product.ProductAPI.services;

import com.Product.ProductAPI.model.Product;
import com.Product.ProductAPI.model.ProductDTO;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public interface ProductService {

    Object getBySku(String sku) throws IOException, InterruptedException;

    Object internalGetBySku (String sku);

    List<Product> getBySkuOrDesignation (String skuOrDesignation) throws IOException, InterruptedException;

    List<Product> internalGetBySkuOrDesignation (String skuOrDesignation);

    List<ProductDTO> getCatalog() throws IOException, InterruptedException;
    Product create(Product pt) throws IOException, InterruptedException;

    BufferedImage generateCode128BarcodeImage(String barcodeText);

}
