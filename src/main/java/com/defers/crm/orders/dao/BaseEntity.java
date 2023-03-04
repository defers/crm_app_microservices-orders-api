package com.defers.crm.orders.dao;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    @CreatedDate
    LocalDateTime createdDate;
    @LastModifiedDate
    LocalDateTime updatedDate;
    boolean deleted;
}
