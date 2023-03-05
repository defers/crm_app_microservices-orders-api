package com.defers.crm.orders.service;

import com.defers.crm.orders.dto.OrderDTORequest;
import com.defers.crm.orders.dto.OrderDTOResponse;
import com.defers.crm.orders.mapper.OrderDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
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
        return orderDTOResponseMapper.convertToDto(orderService.findById(id));
    }


    @Transactional(readOnly = false)
    @Override
    public OrderDTOResponse save(OrderDTORequest orderDTORequest) {
        var order = orderDTORequestMapper.convertToEntity(orderDTORequest);
        order = orderService.save(order);
        return orderDTOResponseMapper.convertToDto(order);
    }
}
