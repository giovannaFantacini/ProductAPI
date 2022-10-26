package com.Product.ProductAPI.controllers;

import com.Product.ProductAPI.model.Product;
import com.Product.ProductAPI.repository.ProductRepository;
import com.Product.ProductAPI.services.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductRepository repository;
    @Autowired
    private ProductServiceImp service;

    @GetMapping
    public ResponseEntity<Product> getBySku(@RequestParam("sku") final String sku) throws IOException, InterruptedException {
        final Product product = (Product) service.getBySku(sku);
        return ResponseEntity.ok().body(product);
    }

    @GetMapping(value = "/search")
    public Iterable<Product> getBySkuOrDesignation(@RequestParam("skuOrDesignation") final String skuOrDesignation,@RequestParam("offset") final int offset, @RequestParam("pageSize") final int pageSize) {
        return service.getBySkuOrDesignation(skuOrDesignation,offset,pageSize);

    }

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
      public Product createProduct(@RequestBody final Product pt) throws IOException {
      return this.repository.save(pt);
    }

}
