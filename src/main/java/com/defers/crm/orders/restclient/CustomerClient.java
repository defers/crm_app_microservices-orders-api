package com.defers.crm.orders.restclient;

import com.defers.crm.orders.dto.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${restclient.crm-customers-microservice.name}")
public interface CustomerClient {
    @GetMapping("${restclient.crm-customers-microservice.basepath}/{id}")
    CustomerDTO getCustomerById(@PathVariable String id);
}
