package com.devmarvin.pizzalicious.web.controller;

import com.devmarvin.pizzalicious.persistence.entity.CustomerEntity;
import com.devmarvin.pizzalicious.persistence.entity.OrderEntity;
import com.devmarvin.pizzalicious.service.CustomerService;
import com.devmarvin.pizzalicious.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final OrderService orderService;
    public CustomerController(CustomerService customerService, OrderService orderService) {
        this.customerService = customerService;
        this.orderService = orderService;
    }

    //Obtener un cliente por su telefono
    @GetMapping("/phone/{phone}")
    public ResponseEntity<CustomerEntity> getByPhone(@PathVariable String phone) {
        return ResponseEntity.ok(this.customerService.findByPhone(phone));
    }

    //Obtener todas las ordenes que ha tenido un cliente en especifico
    @GetMapping("/customer/{idCustomer}")
    public ResponseEntity<List<OrderEntity>> getCustomerOrders(@PathVariable String idCustomer){
        return ResponseEntity.ok(this.orderService.getCustomerOrders(idCustomer));
    }

}
