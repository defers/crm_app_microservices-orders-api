package com.defers.crm.orders.mapper;

import com.defers.crm.orders.dao.Order;
import com.defers.crm.orders.dto.AbstractOrderDTO;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;

@Data
@Slf4j
@SuperBuilder
public class OrderDTOMapper<D extends AbstractOrderDTO> extends AbstractConverterDTO<Order, D> {

    //private OrderRepository orderRepository;

    public OrderDTOMapper(ModelMapper modelMapper, Class<Order> entityClass,
                          Class<D> dtoClass) {
        super(modelMapper, entityClass,  dtoClass);
        //this.orderRepository = orderRepository;
    }

    @PostConstruct
    public void setupMapper() {
//        modelMapper.createTypeMap(entityClass, dtoClass)
//                .addMappings(m -> m.skip(D::setUsername))
//                .setPostConverter(toDtoConverter());
//        modelMapper.createTypeMap(dtoClass, entityClass)
//                .addMappings(m -> m.skip(Order::setUser))
//                .setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(Order source, D destination) {
        //destination.setUsername(getUserName(source));
    }

//    private String getUserName(Order source) {
//        return Objects
//                .requireNonNullElse(source.getUser(), Order.builder().build())
//                .getUsername();
//    }

    @Override
    void mapSpecificFields(D source, Order destination) {
//        var username = source.getUsername();
//        if (Objects.nonNull(username)) {
//            destination.setUser(
//                    Order.builder()
//                            .username(username)
//                            .build()
//            );
//        }
    }
}
