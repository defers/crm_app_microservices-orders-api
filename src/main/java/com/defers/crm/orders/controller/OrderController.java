package com.defers.crm.orders.controller;

import com.defers.crm.orders.dto.ApiResponse;
import com.defers.crm.orders.dto.OrderDTORequest;
import com.defers.crm.orders.dto.OrderDTOResponse;
import com.defers.crm.orders.enums.ResponseApiStatus;
import com.defers.crm.orders.service.OrderServiceDTOImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Data
@AllArgsConstructor
@RequestMapping("/v1/orders")
public class OrderController {
    @Autowired
    private OrderServiceDTOImpl orderServiceDTO;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ApiResponse<List<OrderDTOResponse>> getAll(
            @RequestParam(required = false, defaultValue = "${app.data.default-page-number}") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "${app.data.default-number-on-page}") Integer numberOnPage
    ) {
        return ApiResponse.<List<OrderDTOResponse>>builder()
                .responseApiStatus(ResponseApiStatus.OK)
                .data(orderServiceDTO.findAllDTO(pageNumber, numberOnPage))
                .build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ApiResponse<OrderDTOResponse> save(@RequestBody @Valid OrderDTORequest orderDTORequest) {
        return ApiResponse.<OrderDTOResponse>builder()
                .responseApiStatus(ResponseApiStatus.OK)
                .data(orderServiceDTO.save(orderDTORequest))
                .build();
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}")
    public ApiResponse<OrderDTOResponse> getById(@PathVariable int id) {
        OrderDTOResponse orderDTOResponse = orderServiceDTO.findDTOById(id);
        return ApiResponse.<OrderDTOResponse>builder()
                .responseApiStatus(ResponseApiStatus.OK)
                .data(orderDTOResponse)
                .build();
    }
}
