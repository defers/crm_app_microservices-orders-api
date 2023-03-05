package com.defers.crm.orders.service;

import com.defers.crm.orders.dto.OrderDTORequest;
import com.defers.crm.orders.dto.OrderDTOResponse;
import com.defers.crm.orders.mapper.OrderDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderServiceDTOImpl implements OderServiceDTO {

    OrderService orderService;
    private OrderDTOMapper<OrderDTOResponse> orderDTOResponseMapper;
    private OrderDTOMapper<OrderDTORequest> orderDTORequestMapper;
    private int defaultPageNumber;
    private int defaultNumberOnPage;

    @Autowired
    public OrderServiceDTOImpl(OrderService orderService,
                               OrderDTOMapper<OrderDTOResponse> orderDTOResponseMapper,
                               OrderDTOMapper<OrderDTORequest> orderDTORequestMapper,
                               @Value(value = "${app.data.default-page-number}") int defaultPageNumber,
                               @Value(value = "${app.data.default-number-on-page}") int defaultNumberOnPage) {
        this.orderService = orderService;
        this.orderDTOResponseMapper = orderDTOResponseMapper;
        this.orderDTORequestMapper = orderDTORequestMapper;
        this.defaultPageNumber = defaultPageNumber;
        this.defaultNumberOnPage = defaultNumberOnPage;
    }

    @Override
    public List<OrderDTOResponse> findAllDTO(Integer pageNumber, Integer numberOnPage) {
        if (Objects.isNull(pageNumber)) {
            pageNumber = defaultPageNumber;
        }
        if (Objects.isNull(numberOnPage)) {
            numberOnPage = defaultNumberOnPage;
        }
        return orderService.findAll(pageNumber, numberOnPage).stream()
                .map(e -> orderDTOResponseMapper.convertToDto(e))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTOResponse findDTOById(int id) {
        return null;
    }


    @Override
    public OrderDTOResponse save(OrderDTORequest orderDTORequest) {
        return null;
    }
}
