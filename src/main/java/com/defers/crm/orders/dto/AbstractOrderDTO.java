package com.defers.crm.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AbstractOrderDTO {
    private long id;
    @NotBlank
    @NotNull
    private String description;
    @Size(min = 0)
    private BigDecimal sum;
    boolean deleted;
}
