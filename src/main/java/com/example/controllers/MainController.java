package com.example.controllers;

import com.example.entities.Product;
import com.example.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    ProductService productService;

    @GetMapping("/index")
    public String homePage() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/shop")
    public String shopPage(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "shop";
    }

    @GetMapping("/details/{id}")
    public String details(Model model, @PathVariable("id") Long id) {
        model.addAttribute("selectedProduct", productService.getProductById(id));
        return "details";
    }

    @GetMapping("/find_by_title")
    public String detailsByTitle(Model model, @RequestParam("title") String title) {
        model.addAttribute("selectedProduct", productService.getProductByTitle(title));
        return "details";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProductById(@PathVariable("id") Long id) {
        productService.deleteProductById(id);
        return "redirect:/shop";
    }
}
