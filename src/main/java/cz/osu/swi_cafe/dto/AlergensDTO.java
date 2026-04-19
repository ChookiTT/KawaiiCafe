package cz.osu.swi_cafe.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlergensDTO {
    private Long alergenId;
    private String alergenName;
}
