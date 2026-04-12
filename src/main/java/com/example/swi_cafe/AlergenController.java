/*package com.example.swi_cafe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/alergens")
public class AlergenController {
    @Autowired
    MenuRepository menuRepository;

    @GetMapping
    public List<Alergens> getAllItems()
    { return menuRepository.findAll();
    }


}*/