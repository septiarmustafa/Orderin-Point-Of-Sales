package com.enigma.orderin.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CashierResponse {
    private Integer id;
    private String name;
    private String mobilePhone;
}
