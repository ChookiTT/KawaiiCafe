package com.example.swi_cafe;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Alergens")
public class Alergens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;
    private String name;
    private String description;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable( name = "item_alergens",
            joinColumns = @JoinColumn(name = "alergens_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")

    )
    private List<MenuItem> items = new ArrayList<>();
    public Alergens() {}

    public Alergens(Long number, String name, String description) {
        this.number = number;
        this.name = name;
        this.description = description;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }
}
