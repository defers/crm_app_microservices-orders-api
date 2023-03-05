package com.defers.crm.orders.dao;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    LocalDateTime createdDate;
    @UpdateTimestamp
    @Column(name = "updated_date")
    LocalDateTime updatedDate;
    @Column(name = "deleted", nullable = false)
    boolean deleted;
}
