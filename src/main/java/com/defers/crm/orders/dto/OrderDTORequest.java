package com.defers.crm.orders.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@SuperBuilder
public class OrderDTORequest extends AbstractOrderDTO{
    @NotBlank
    @NotNull
    private String customerId;
}
