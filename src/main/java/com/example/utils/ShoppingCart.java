package com.example.utils;

import com.example.entities.Product;
import com.example.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCart {
    private List<Product> products;

    @Autowired
    private ProductService productService;

    public List<Product> getProducts() {
        return products;
    }

    @PostConstruct
    public void init() {
        products = new ArrayList<>();
    }

    public void addProductById(Long id) {
        products.add(productService.getProductById(id));
    }

    public void deleteProductById(Long id) {
        products.remove(productService.getProductById(id));
    }
}
