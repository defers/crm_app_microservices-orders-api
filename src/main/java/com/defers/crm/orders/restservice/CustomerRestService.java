package com.defers.crm.orders.restservice;

import com.defers.crm.orders.dto.CustomerDTO;
import com.defers.crm.orders.restclient.CustomerClient;
import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRestService {
    @Autowired
    private CustomerClient customerClient;

    public CustomerDTO getCustomerById(String id) {
        return customerClient.getCustomerById(id);
    }

    public boolean checkCustomerWithIdIsExists(String id) {
        boolean isExists = false;
        try {
            CustomerDTO customerDTO = getCustomerById(id);
            isExists = true;
        }
        catch (FeignException e) {
            isExists = false;
        }
        return isExists;
    }
}
