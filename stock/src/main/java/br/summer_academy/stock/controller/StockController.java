package br.summer_academy.stock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.summer_academy.stock.model.Product;
import br.summer_academy.stock.repository.StockRepository;

@RestController
public class StockController {

    @Autowired
    StockRepository repository;

    @GetMapping("/products")
        List<Product> getAll() {
        return repository.findAll();
    }
}
