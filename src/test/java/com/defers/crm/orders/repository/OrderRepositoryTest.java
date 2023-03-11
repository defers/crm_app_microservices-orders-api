package com.defers.crm.orders.repository;

import com.defers.crm.orders.dao.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles(value = "test")
@DataJpaTest
class OrderRepositoryTest {
    @Autowired
    OrderRepository orderRepository;

    @Test
    @Sql({"/sql/create_orders.sql"})
    public void findAll() {
        List<Order> orders = orderRepository.findAll();
        Assertions.assertThat(orders)
                .isNotEmpty()
                .hasSize(2)
                .first().hasFieldOrPropertyWithValue("id", 1)
                .hasFieldOrPropertyWithValue("customerId", "fa994eca-23b4-4cb2-abed-cf7d16fa3735");
    }

}