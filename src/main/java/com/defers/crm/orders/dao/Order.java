package com.defers.crm.orders.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@SuperBuilder
@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseEntity {
    @Id
    @SequenceGenerator(name = "orders_id_generator", sequenceName = "orders_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_id_generator")
    private int id;
    @NotNull
    @NotBlank
    @Column(name = "customer_id")
    private String customerId;
    private String description;
    @DecimalMin(value = "0.0")
    @Column(precision=10, scale=2)
    private BigDecimal sum;
}
