package com.enigma.orderin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OrderDetailResponse {
    private Integer orderDetailId;
    private ProductResponse product;
    private Integer quantity;
}
