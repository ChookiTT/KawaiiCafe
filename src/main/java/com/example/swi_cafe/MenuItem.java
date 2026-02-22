package com.example.swi_cafe;

import jakarta.persistence.*;

@Entity
@Table(name = "menu")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nazev;
    private double cena;

    public MenuItem() {
    }
    public MenuItem(String nazev, double cena) {
        this.nazev = nazev;
        this.cena = cena;
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
