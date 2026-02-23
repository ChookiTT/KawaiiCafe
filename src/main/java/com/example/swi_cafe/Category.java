package com.example.swi_cafe;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;


    @Entity
    @Table(name = "categories")
    public class Category {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;

        @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
        @JsonIgnore // Important: Stops the infinite loop when viewing JSON in browser
        private List<MenuItem> items;

        public Category() {}

        // Getters and Setters
        public Long getId() { return id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public List<MenuItem> getItems() { return items; }
    }

