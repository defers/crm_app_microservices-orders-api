package com.defers.crm.orders.controller;

import com.defers.crm.orders.dto.ApiResponse;
import com.defers.crm.orders.dto.CustomerDTO;
import com.defers.crm.orders.dto.OrderDTORequest;
import com.defers.crm.orders.dto.OrderDTOResponse;
import com.defers.crm.orders.enums.ResponseApiStatus;
import com.defers.crm.orders.service.OrderServiceDTOImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
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
@TestPropertySource(locations = "classpath:application-test.yml")
class OrderControllerCustomMockMVCTest {

    private MockMvc mvc;
    private List<OrderDTOResponse> ordersDTOs;
    @Mock
    private OrderServiceDTOImpl orderService;
    @Autowired
    @InjectMocks
    private OrderController orderController;
    private ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new ParameterNamesModule())
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule());

    @TestConfiguration
    public static class OrderControllerTestConfig {
        @Bean
        public ObjectMapper objectMapper() {
            return new ObjectMapper();
        }

    }

    @BeforeAll
    public static void setUpBeforeAll() {
    }

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
    void save_ShouldReturnSavedDTOWhenSaved() throws Exception {
        String customerId = "fa994eca-23b4-4cb2-abed-cf7d16fa3735";
        OrderDTORequest orderDTO1 = OrderDTORequest.builder()
                .id(1)
                .description("info order 1")
                .sum(BigDecimal.valueOf(1000))
                .customerId(customerId)
                .build();

        ApiResponse<OrderDTOResponse> expectedResponse = ApiResponse.<OrderDTOResponse>builder()
                .responseApiStatus(ResponseApiStatus.OK)
                .data(ordersDTOs.get(0))
                .build();

        String orderString = objectMapper.writeValueAsString(orderDTO1);

        BDDMockito.given(orderService.save(orderDTO1))
                .willReturn(ordersDTOs.get(0));

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/v1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(orderString))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value(1))
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();
        JSONAssert.assertEquals(null,
                objectMapper.writeValueAsString(expectedResponse),
                responseContent,
                true);
    }

    @Test
    void getById_ShouldReturnDTOFoundByIdWhenFindById() throws Exception {

        OrderDTOResponse orderDTOResponse = ordersDTOs.get(0);
        int id = orderDTOResponse.getId();

        Mockito.when(orderService.findDTOById(id))
                .thenReturn(orderDTOResponse);

        mvc.perform(MockMvcRequestBuilders.get(String.format("/v1/orders/%s", id))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id")
                        .value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.description")
                        .value(orderDTOResponse.getDescription()));
    }

}