package cz.osu.swi_cafe.tables;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
    public class Category {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long categoryId;
        private String categoryName;

        @ManyToMany(mappedBy = "categories", cascade = CascadeType.ALL)
        @JsonBackReference
        private List<MenuItem> items;

    public Category(String categoryName, List<MenuItem> items) {
        this.categoryName = categoryName;
        this.items = items;
    }

}

