package com.example.swi_cafe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
    @RequestMapping("/menu")
    public class MenuController {
        @Autowired
        MenuRepository menuRepository;

        @GetMapping
        public List<MenuItem> getAllItems()
        { return menuRepository.findAll();
        }

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/categories")
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }
    }

