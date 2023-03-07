package com.defers.crm.orders.controller;

import com.defers.crm.orders.dto.CustomerDTO;
import com.defers.crm.orders.dto.OrderDTOResponse;
import com.defers.crm.orders.service.OrderServiceDTOImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MultiValueMapAdapter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class OrderControllerCustomMockMVCTest {

    private MockMvc mvc;
    private List<OrderDTOResponse> ordersDTOs;
    @Mock
    private OrderServiceDTOImpl orderService;
    @Autowired
    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(orderController)
                .build();

        CustomerDTO customer = CustomerDTO.builder()
                .id("fa994eca-23b4-4cb2-abed-cf7d16fa3735")
                .name("Partner1")
                .type("Corporate")
                .build();

        OrderDTOResponse orderDTO1 = OrderDTOResponse.builder()
                .id(1)
                .description("info order 1")
                .sum(BigDecimal.valueOf(1000))
                .customer(customer)
                .createdDate(LocalDateTime.of(2023, 1, 1, 17, 10))
                .build();

        OrderDTOResponse orderDTO2 = OrderDTOResponse.builder()
                .id(2)
                .description("info order 2")
                .sum(BigDecimal.valueOf(2000))
                .customer(customer)
                .createdDate(LocalDateTime.of(2023, 3, 5, 15, 10))
                .build();

        this.ordersDTOs = List.of(orderDTO1, orderDTO2);
    }

    @Test
    void getAll_ShouldReturnTwoOrdersWhenGet() throws Exception {
        String pageNumber = "0";
        String numberOnPage = "10";

        Map<String, List<String>> params = new HashMap<>();
        params.put("pagenumber", List.of(pageNumber));
        params.put("numberonpage", List.of(numberOnPage));

        Mockito.when(orderService.findAllDTO(Integer.valueOf(pageNumber), Integer.valueOf(numberOnPage)))
                .thenReturn(ordersDTOs);

        mvc.perform(MockMvcRequestBuilders.get("/v1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .params(new MultiValueMapAdapter<>(params)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].description")
                        .value(ordersDTOs.get(0).getDescription()));
    }

    @Test
    void save() {
    }

    @Test
    void getById() {
    }
}