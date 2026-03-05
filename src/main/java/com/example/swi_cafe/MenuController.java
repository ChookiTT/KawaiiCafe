package com.example.swi_cafe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin(origins = "*")
@RestController
    @RequestMapping("/menu")
    public class MenuController {
        @Autowired
        MenuRepository menuRepository;

        @GetMapping
        public List<MenuItem> getAllItems()
        { return menuRepository.findAll();
        }


    }

