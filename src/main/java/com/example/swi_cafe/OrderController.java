/* package com.example.swi_cafe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    MenuRepository menuRepository;

    @GetMapping
    public List<Order> getAllItems()
    { return menuRepository.findAll();
    }


} */