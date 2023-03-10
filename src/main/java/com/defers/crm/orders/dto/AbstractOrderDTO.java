package com.defers.crm.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AbstractOrderDTO {
    private int id;
    @NotBlank
    @NotNull
    private String description;
    @DecimalMin(value = "0.0")
    private BigDecimal sum;
    boolean deleted;
}
