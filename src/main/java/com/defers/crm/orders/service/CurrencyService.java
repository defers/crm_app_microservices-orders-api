package com.defers.crm.orders.service;

import com.defers.crm.orders.dao.Currency;

import java.util.List;

public interface CurrencyService {
    List<Currency> findAll(Integer pageNumber, Integer numberOnPage);
    Currency findById(String id);
    Currency save(Currency currency);
}

