package com.defers.crm.orders.repository;

import com.defers.crm.orders.dao.Currency;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends PagingAndSortingRepository<Currency, String> {
}
