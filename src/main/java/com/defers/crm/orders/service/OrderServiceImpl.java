package com.defers.crm.orders.service;

import com.defers.crm.orders.dao.Order;
import com.defers.crm.orders.repository.OrderRepository;
import com.defers.crm.orders.util.MessagesUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Locale;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private MessageSource messageSource;

    @Override
    public List<Order> findAll(Integer pageNumber, Integer numberOnPage) {
        Pageable page = PageRequest.of(pageNumber, numberOnPage);
        return orderRepository.findAll(page).getContent();
    }

    @Override
    public Order findById(int id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> {
                    var msg = MessagesUtils.getFormattedMessage(
                            messageSource.getMessage("exception.order-not-found",
                                    null,
                                    Locale.getDefault()), id);
                    return new EntityNotFoundException(msg);
                });
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

}
