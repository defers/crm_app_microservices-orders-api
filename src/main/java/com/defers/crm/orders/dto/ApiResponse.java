package com.defers.crm.orders.dto;

import com.defers.crm.orders.enums.ResponseApiStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private ResponseApiStatus responseApiStatus;
    private T data;
}
