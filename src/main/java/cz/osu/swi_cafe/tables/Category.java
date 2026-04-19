package cz.osu.swi_cafe.tables;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

        @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
        @JsonIgnore
        private List<MenuItem> items;

    }

