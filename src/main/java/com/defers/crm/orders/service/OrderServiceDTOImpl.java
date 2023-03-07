package com.defers.crm.orders.service;

import com.defers.crm.orders.dto.CustomerDTO;
import com.defers.crm.orders.dto.OrderDTORequest;
import com.defers.crm.orders.dto.OrderDTOResponse;
import com.defers.crm.orders.mapper.OrderDTOMapper;
import com.defers.crm.orders.restservice.CustomerRestService;
import com.defers.crm.orders.util.ExceptionUtils;
import com.defers.crm.orders.util.MessagesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
public class OrderServiceDTOImpl implements OderServiceDTO {

    private OrderService orderService;
    private OrderDTOMapper<OrderDTOResponse> orderDTOResponseMapper;
    private OrderDTOMapper<OrderDTORequest> orderDTORequestMapper;
    private CustomerRestService customerRestService;
    private int defaultPageNumber;
    private int defaultNumberOnPage;

    @Autowired
    public OrderServiceDTOImpl(OrderService orderService,
                               OrderDTOMapper<OrderDTOResponse> orderDTOResponseMapper,
                               OrderDTOMapper<OrderDTORequest> orderDTORequestMapper,
                               CustomerRestService customerRestService,
                               @Value(value = "${app.data.default-page-number}") int defaultPageNumber,
                               @Value(value = "${app.data.default-number-on-page}") int defaultNumberOnPage) {
        this.orderService = orderService;
        this.orderDTOResponseMapper = orderDTOResponseMapper;
        this.orderDTORequestMapper = orderDTORequestMapper;
        this.customerRestService = customerRestService;
        this.defaultPageNumber = defaultPageNumber;
        this.defaultNumberOnPage = defaultNumberOnPage;
    }

    @Override
    public List<OrderDTOResponse> findAllDTO(Integer pageNumber, Integer numberOnPage) {
        if (Objects.isNull(pageNumber)) {
            pageNumber = Objects.isNull(defaultPageNumber) ? 0 : defaultPageNumber;
        }
        if (Objects.isNull(numberOnPage)) {
            numberOnPage = Objects.isNull(defaultNumberOnPage) ? 10 : defaultNumberOnPage;
        }
        return orderService.findAll(pageNumber, numberOnPage).stream()
                .map(e -> {
                    var dto = orderDTOResponseMapper.convertToDto(e);
                    setCustomer(dto);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTOResponse findDTOById(int id) {
        OrderDTOResponse orderDTOResponse = orderDTOResponseMapper.convertToDto(orderService.findById(id));
        setCustomer(orderDTOResponse);
        return orderDTOResponse;
    }

    private void setCustomer(OrderDTOResponse orderDTOResponse) {
        Objects.requireNonNull(orderDTOResponse);
        CustomerDTO customerDTO = customerRestService.getCustomerById(orderDTOResponse.getCustomer().getId());
        orderDTOResponse.setCustomer(customerDTO);
    }

    private void checkCustomer(String customerId) {
        Objects.requireNonNull(customerId);
        boolean isExists = customerRestService.checkCustomerWithIdIsExists(customerId);
        if (!isExists) {
            ExceptionUtils.throwException(EntityNotFoundException.class,
                    MessagesUtils.getFormattedMessage("Customer with id %s not found", customerId));
        }
    }

    @Transactional(readOnly = false)
    @Override
    public OrderDTOResponse save(OrderDTORequest orderDTORequest) {
        var order = orderDTORequestMapper.convertToEntity(orderDTORequest);
        checkCustomer(orderDTORequest.getCustomerId());
        order = orderService.save(order);
        OrderDTOResponse orderDTOResponse = orderDTOResponseMapper.convertToDto(order);
        setCustomer(orderDTOResponse);
        return orderDTOResponse;
    }
}
