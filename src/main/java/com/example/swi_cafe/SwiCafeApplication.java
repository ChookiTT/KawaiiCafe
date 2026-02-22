package com.example.swi_cafe;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SwiCafeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwiCafeApplication.class, args);
    }
    @Bean
    public CommandLineRunner runDataTest(MenuRepository repository) {
        return args -> {
            // This is where you put your code to add to the "Giant Pile"
            repository.save(new MenuItem("Chocolate Cake", 30.00));
            repository.save(new MenuItem("Americano", 70.00));

            System.out.println("Items have been saved to the database!");
        };
    }
}
