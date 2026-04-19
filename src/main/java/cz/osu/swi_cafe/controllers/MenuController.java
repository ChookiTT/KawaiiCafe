package cz.osu.swi_cafe.controllers;

import cz.osu.swi_cafe.tables.MenuItem;
import cz.osu.swi_cafe.repos.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin(origins = "*")
@RestController
    @RequestMapping("/api/menu")
    public class MenuController {
        @Autowired
        MenuRepository menuRepository;

        @GetMapping
        public List<MenuItem> getAllItems()
        { return menuRepository.findAll();
        }


    }

