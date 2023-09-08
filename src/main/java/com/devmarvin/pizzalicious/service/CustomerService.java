package com.devmarvin.pizzalicious.service;

import com.devmarvin.pizzalicious.persistence.entity.CustomerEntity;
import com.devmarvin.pizzalicious.persistence.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) { //Aqui estamos creando un constructor que recibe un customer repository para poder inyectarlo en el servicio
        this.customerRepository = customerRepository;
    }

    //Obtener un cliente por su telefono
    public CustomerEntity findByPhone(String phone) {
        return this.customerRepository.findByPhone(phone);
    }


}
