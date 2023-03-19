package com.defers.crm.orders.input;

import com.defers.crm.orders.dao.Currency;
import com.defers.crm.orders.service.CurrencyService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Data
@Slf4j
@AllArgsConstructor
@Service
public class MessageListener {
    @Autowired
    private CurrencyService currencyService;

    @KafkaListener(topics = "currency", groupId = "currency")
    public void listenCurrencyTopic(Currency currency) {
        System.out.println(currency);
        log.info("Get currency from kafka {}", currency);
        currencyService.save(currency);
    }
}
