package com.defers.crm.orders.mapper;

import com.defers.crm.orders.dao.BaseEntity;

public interface ConverterDTO<E extends BaseEntity, D>{
    D convertToDto(E entity);
    E convertToEntity(D dto);
}
