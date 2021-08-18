package com.codecool.shopspring.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private OrderService service;

    @Test
    void shouldReturnNoOrdersWhenAskedForAllOrdersAndTheyAreNone() throws Exception {
        given(service.findAll()).willReturn(List.of());
        mockMvc.perform(get("/orders/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(List.of())))
                .andReturn();
    }

    @Test
    void shouldReturnOrderByIdWhenAskedAndThereIsOne() throws Exception {
        final var order = new Order();
        order.setId(1L);
        given(service.findOrderById(any())).willReturn(Optional.of(order));
        mockMvc.perform(get("/orders/1/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(order.getId().intValue())))
                .andReturn();
    }

    @Test
    void shouldReturnNotFoundWhenAskedForOrderAndThereIsNot() throws Exception {
        given(service.findOrderById(any())).willReturn(Optional.empty());
        mockMvc.perform(get("/orders/1/"))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void shouldCreateOrderAndReturnIt() throws Exception {
        final var title = "The title";
        final var order = new Order();
        order.setTitle(title);
        given(service.createOrder(any())).willReturn(order);
        mockMvc.perform(post("/orders/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(order)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", is(title)))
                .andReturn();
    }

    @Test
    void shouldAddProductReferenceInOrder() {
        //todo
    }

    @Test
    void updateOrder() {
        //todo
    }

    @Test
    void deleteOrder() {
        //todo
    }
}