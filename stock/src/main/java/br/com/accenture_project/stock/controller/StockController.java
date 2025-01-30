package br.com.accenture_project.stock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.accenture_project.stock.model.Product;
import br.com.accenture_project.stock.repository.StockRepository;

@RestController
public class StockController {

    @Autowired
    StockRepository repository;

    @GetMapping("/products")
        List<Product> getAll() {
        return repository.findAll();
    }
}
