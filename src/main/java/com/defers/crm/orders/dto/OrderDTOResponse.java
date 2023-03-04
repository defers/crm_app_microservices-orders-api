package com.defers.crm.orders.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
public class OrderDTOResponse extends AbstractOrderDTO {
    @NotBlank
    @NotNull
    private CustomerDTO customer;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
