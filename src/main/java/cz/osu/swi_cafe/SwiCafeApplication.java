package cz.osu.swi_cafe;

import cz.osu.swi_cafe.repos.AlergenRepository;
import cz.osu.swi_cafe.repos.CategoryRepository;
import cz.osu.swi_cafe.repos.MenuRepository;
import cz.osu.swi_cafe.tables.Alergen;
import cz.osu.swi_cafe.tables.Category;
import cz.osu.swi_cafe.tables.MenuItem;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.tags.ArgumentAware;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SwiCafeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwiCafeApplication.class, args);
    }

    @Bean
    public CommandLineRunner runDataTest(MenuRepository repository,AlergenRepository repository2, CategoryRepository categoryRepository) {
        return args -> {
           /* Alergen Mliko = repository2.findById(2L)
            .orElseThrow(() -> new RuntimeException("Alergen mlíka nebyl v DB nalezen!"));
            Alergen egg = repository2.findById(4L).orElseThrow(() -> new RuntimeException("Alergen vajec nebyl v DB nalezen!"));
            Alergen lepek = repository2.findById(1L).orElseThrow(() -> new RuntimeException("Alergen lepek nebyl v DB nalezen!"));
            Alergen Nuts = repository2.findById(3L).orElseThrow(() -> new RuntimeException("Alergen ořechů nebyl v DB nalezen!"));
            Category yums= categoryRepository.findById(1L)
                            .orElseThrow(() -> new RuntimeException("Kategorie kafe nebyla v DB nalezen!"));
            List<Category> kategories = new ArrayList<>();
            kategories.add(yums);
            List<Alergen> alergeny=new ArrayList<>();
            alergeny.add(Mliko);
            alergeny.add(egg);
            alergeny.add(lepek);

             */
                };
            }
            };






