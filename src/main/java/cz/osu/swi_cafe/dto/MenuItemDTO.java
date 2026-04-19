package cz.osu.swi_cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemDTO {
    private Long itemId;
    private String itemName;
    private double itemPrice;
}
