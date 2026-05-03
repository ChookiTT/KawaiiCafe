package cz.osu.swi_cafe.tables;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "menuItem")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;
    @Column(nullable = false)
    private String itemName;
    @Column(nullable = false)
    private double itemPrice;
    private String imagePath;

    @ManyToMany(fetch = FetchType.LAZY)

    @JoinTable(
            name = "category_item",
            joinColumns = @JoinColumn(name = "menu_item_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories = new ArrayList<>();


    @ManyToMany(mappedBy = "items")
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "alergen_item",
            joinColumns = @JoinColumn(name = "menu_item_id",nullable = false),
            inverseJoinColumns = @JoinColumn(name = "alergen_id")
    )
    private List<Alergen> alergen = new ArrayList<>();

    public MenuItem(String itemName, double itemPrice, List<Alergen> alergeny, List<Category> categories) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.alergen =alergeny;
        this.categories = categories;
        this.orders = new ArrayList<>();
    }
}
