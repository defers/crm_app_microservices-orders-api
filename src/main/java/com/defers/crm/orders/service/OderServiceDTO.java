package com.defers.crm.orders.service;

import com.defers.crm.orders.dto.OrderDTORequest;
import com.defers.crm.orders.dto.OrderDTOResponse;

import java.util.List;

public interface OderServiceDTO {
    List<OrderDTOResponse> findAllDTO(Integer pageNumber, Integer numberOnPage);
    OrderDTOResponse findDTOById(int id);
    OrderDTOResponse save(OrderDTORequest orderDTORequest);
}
