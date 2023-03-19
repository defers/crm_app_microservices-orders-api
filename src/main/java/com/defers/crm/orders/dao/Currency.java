package com.defers.crm.orders.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Table(name = "currency")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
@Entity
public class Currency extends BaseEntity{
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "currency_id_generator")
//    @SequenceGenerator(name = "currency_id_generator", sequenceName = "currency_id_seq", allocationSize = 1)
    @Id
    private String id;
    @NotNull
    @NotBlank
    private String name;
    private String code;
}
