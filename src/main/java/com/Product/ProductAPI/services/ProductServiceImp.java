package com.Product.ProductAPI.services;

import com.Product.ProductAPI.model.Product;
import com.Product.ProductAPI.model.ProductDTO;
import com.Product.ProductAPI.repository.Product2Repository;
import com.Product.ProductAPI.repository.ProductRepository;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductServiceImp implements ProductService{

    @Autowired
    private ProductRepository repository;

    @Autowired
    private Product2Repository productRepository;

    @Override
    public List<ProductDTO> getCatalog() throws IOException, InterruptedException {
        return Stream.concat(repository.getCatalog().stream(), productRepository.getCatalog().stream()).collect(Collectors.toList());
    }

    @Override
    public Object getBySku(final String sku) throws IOException, InterruptedException {
        Optional<Product> productOptional = repository.findById(sku);
        boolean isPresent = productOptional.isPresent();
        if(!isPresent){
            return productRepository.getProduct(sku);
        }
        return productOptional.get();
    }

    @Override
    public List<Product> getBySkuOrDesignation(String skuOrDesignation) throws IOException, InterruptedException {
        return Stream.concat(repository.getBySkuOrDesignation(skuOrDesignation).stream(), productRepository.getProductBySkuOrDesignation(skuOrDesignation).stream()).collect(Collectors.toList());
    }

    @Override
    public Product create(Product pt) throws IOException {
        return repository.save(pt);
    }

    @Override
    public BufferedImage generateCode128BarcodeImage(String barcodeText) {
        Code128Bean barcodeGenerator = new Code128Bean();
        BitmapCanvasProvider canvas = new BitmapCanvasProvider(160, BufferedImage.TYPE_BYTE_BINARY, false, 0);

        barcodeGenerator.generateBarcode(canvas, barcodeText);
        return canvas.getBufferedImage();
    }

}
