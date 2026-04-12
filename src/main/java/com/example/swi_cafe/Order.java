package com.example.swi_cafe;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Cafe_Order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nazev;
    private double cena;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
        @JoinTable( name = "order_menu",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")

    )
    private List<MenuItem> items = new ArrayList<>();
    public Order() {}

    public Order(Long id, double cena, String nazev) {
        this.id = id;
        this.cena = cena;
        this.nazev = nazev;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }
}
