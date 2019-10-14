package com.example.utils;

import com.example.entities.OrderItem;
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
    private List<OrderItem> items;

    @Autowired
    private ProductService productService;

    public List<OrderItem> getItems() {
        return items;
    }

    @PostConstruct
    public void init() {
        items = new ArrayList<>();
    }

    public void addProductById(Long id) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(productService.getProductById(id));
        items.add(orderItem);
    }

    public void deleteProductById(Long id) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(productService.getProductById(id));
        items.remove(orderItem);
    }
}
