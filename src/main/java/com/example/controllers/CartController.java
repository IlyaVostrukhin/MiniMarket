package com.example.controllers;

import com.example.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ShoppingCart cart;

    @GetMapping("")
    public String showCart(Model model) {
        model.addAttribute("products", cart.getProducts());
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
}
