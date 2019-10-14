package com.example.controllers;

import com.example.entities.Order;
import com.example.services.OrderService;
import com.example.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ShoppingCart cart;

    @Autowired
    private OrderService orderService;

    @GetMapping("")
    public String showCart(Model model) {
        model.addAttribute("items", cart.getItems());
        return "cart";
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable("id") Long id) {
        cart.addProductById(id);
        return "redirect:/shop";
    }

    @GetMapping("/delete/{id}")
    public String deleteProductWithCart(@PathVariable("id") Long id) {
        cart.deleteProductById(id);
        return "redirect:/cart";
    }

    @GetMapping("/create_order")
    public String createOrder(Principal principal) {
        Order order = new Order();
        order.setItems(new ArrayList<>());
        order.setUsername(principal.getName());
        cart.getItems().forEach(i -> {
            order.getItems().add(i);
            i.setOrder(order);
        });
        cart.getItems().clear();
        orderService.saveOrder(order);
        return "redirect:/shop";
    }
}
