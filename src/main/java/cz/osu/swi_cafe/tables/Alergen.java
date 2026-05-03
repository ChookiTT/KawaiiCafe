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


    @ManyToMany(mappedBy = "alergen",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<MenuItem> alergens = new ArrayList<>();

    public Alergen( String alergenName) {
        this.alergenName = alergenName;

    }
}
