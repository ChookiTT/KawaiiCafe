package cz.osu.swi_cafe.tables;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Cafe_Order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private String orderNote;
    private double orderPrice;
    private LocalDateTime orderDate;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
        @JoinTable( name = "order_menu",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")

    )
    private List<MenuItem> items = new ArrayList<>();

}
