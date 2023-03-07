package com.defers.crm.orders.controller;

import com.defers.crm.orders.dto.ApiResponse;
import com.defers.crm.orders.dto.CustomerDTO;
import com.defers.crm.orders.dto.OrderDTOResponse;
import com.defers.crm.orders.enums.ResponseApiStatus;
import com.defers.crm.orders.service.OrderServiceDTOImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.MultiValueMapAdapter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebMvcTest(controllers = OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mvc;
    private List<OrderDTOResponse> ordersDTOs;
    @MockBean
    private OrderServiceDTOImpl orderService;
    @Autowired
    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    public void setUp() {
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
        int pageNumber = 0;
        int numberOnPage = 10;

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

        ApiResponse<List<OrderDTOResponse>> ordersResponse = ApiResponse.<List<OrderDTOResponse>>builder()
                .responseApiStatus(ResponseApiStatus.OK)
                .data(List.of(orderDTO1, orderDTO2))
                .build();
        Map<String, List<String>> params = new HashMap<>();
        params.put("pagenumber", List.of(String.valueOf(pageNumber)));
        params.put("numberonpage", List.of(String.valueOf(numberOnPage)));

        Mockito.when(orderService.findAllDTO(pageNumber, numberOnPage))
                .thenReturn(List.of(orderDTO1, orderDTO2));

        mvc.perform(MockMvcRequestBuilders.get(
        //        String.format("/v1/orders?pagenumber=%s&numberonpage=%s", pageNumber, numberOnPage)
                "/v1/orders"
        )
                .contentType(MediaType.APPLICATION_JSON).params(new MultiValueMapAdapter<>(params)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty());
    }

    @Test
    void save() {
    }

    @Test
    void getById() {
    }
}