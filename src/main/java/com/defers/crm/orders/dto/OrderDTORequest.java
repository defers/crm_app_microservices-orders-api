package com.defers.crm.orders.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class OrderDTORequest extends AbstractOrderDTO{
    @NotBlank
    @NotNull
    private String customerId;
}
