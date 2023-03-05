package com.defers.crm.orders.dao;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@SuperBuilder
@Entity
@Table(name = "order")
public class Order extends BaseEntity {
    @Id
    @SequenceGenerator(name = "order_id_generator", sequenceName = "order_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_generator")
    private int id;
    @NotNull
    @NotBlank
    @Column(name = "customer_id")
    private String customerId;
    private String description;
    @Size(min = 0)
    private BigDecimal sum;


}
