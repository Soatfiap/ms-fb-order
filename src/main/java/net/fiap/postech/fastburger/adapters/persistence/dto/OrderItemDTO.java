package net.fiap.postech.fastburger.adapters.persistence.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemDTO {
    private String productId;
    private Integer quantity;
}
