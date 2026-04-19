package cz.osu.swi_cafe.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long orderId;
    private String orderNote;
    private double orderPrice;
    private LocalDateTime orderDate;
}
