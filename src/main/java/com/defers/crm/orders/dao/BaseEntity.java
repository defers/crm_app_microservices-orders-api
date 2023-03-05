package com.defers.crm.orders.dao;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    @CreatedDate
    @Column(name = "created_date")
    LocalDateTime createdDate;
    @LastModifiedDate
    @Column(name = "updated_date")
    LocalDateTime updatedDate;
    boolean deleted;
}
