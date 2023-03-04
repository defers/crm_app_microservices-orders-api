package com.defers.crm.orders.configuration;

import com.defers.crm.orders.dao.Order;
import com.defers.crm.orders.dto.OrderDTORequest;
import com.defers.crm.orders.dto.OrderDTOResponse;
import com.defers.crm.orders.mapper.OrderDTOMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConvertersConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setSkipNullEnabled(true)
                .setMatchingStrategy(MatchingStrategies.LOOSE)
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
        return modelMapper;
    }

    @Bean
    public OrderDTOMapper<OrderDTORequest> userDTOMapperRequest(RoleRepository roleRepository) {
        return new OrderDTOMapper<>(modelMapper(), Order.class,
                OrderDTORequest.class, roleRepository);
    }

    @Bean
    public OrderDTOMapper<OrderDTOResponse> userDTOMapperResponse(RoleRepository roleRepository) {
        return new OrderDTOMapper<>(modelMapper(), Order.class,
                OrderDTOResponse.class, roleRepository);
    }

}
