package com.Product.ProductAPI.repository;

import com.Product.ProductAPI.model.Product;
import com.Product.ProductAPI.model.ProductDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Repository
public class Product2Repository {

    public Product getProduct(String sku) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8086/products?sku=" + sku))
                .build();

        HttpResponse response = client.send(request,
                HttpResponse.BodyHandlers.ofString());


        var code = response.statusCode();
        if(code == 200){
            ObjectMapper objectMapper = new ObjectMapper();
            String body = response.body().toString();
            var product = objectMapper.readValue(body, Product.class);
            return product;
        }else {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"This product doesn't exist");
        }

    }

    /*public Page<Product> getProductBySkuOrDesignation(String skuOrDesignation, int offset, int pageSize) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8086//products/catalog?offset=" + offset + "&pageSize=" + pageSize))
                .header(skuOrDesignation, skuOrDesignation)
                .build();

        HttpResponse response = client.send(request,
                HttpResponse.BodyHandlers.ofString());


        var code = response.statusCode();
        if(code == 200){
            ObjectMapper objectMapper = new ObjectMapper();
            String body = response.body().toString();
            Page<Product> product = (Page<Product>) objectMapper.readValue(body, ProductDTO.class);
            return product;
        }else {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"This product doesn't exist");
        }

    }*/
}
