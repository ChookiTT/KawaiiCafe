package cz.osu.swi_cafe.controllers;

import cz.osu.swi_cafe.repos.AlergenRepository;
import cz.osu.swi_cafe.tables.Alergen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
    @RequestMapping("/api/alergens")
    public class AlergenController {
        @Autowired
        private AlergenRepository alergenRepository;

        @GetMapping
        public List<Alergen> getAllAlergens() {
            return alergenRepository.findAll();
        }
    }
