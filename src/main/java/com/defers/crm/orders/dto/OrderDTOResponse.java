package com.defers.crm.orders.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@SuperBuilder
public class OrderDTOResponse extends AbstractOrderDTO {
    @NotBlank
    @NotNull
    private CustomerDTO customer;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
