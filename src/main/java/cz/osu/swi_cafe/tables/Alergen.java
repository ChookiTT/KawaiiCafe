package cz.osu.swi_cafe.tables;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Alergens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Alergen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alergenId;
    private String alergenName;


    @ManyToMany(mappedBy = "alergens")
    private List<MenuItem> items = new ArrayList<>();

}
