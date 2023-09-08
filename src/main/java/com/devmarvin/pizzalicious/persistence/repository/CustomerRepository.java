package com.devmarvin.pizzalicious.persistence.repository;

import com.devmarvin.pizzalicious.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends ListCrudRepository<CustomerEntity, String> {

    //Buscar un cliente por su telefono
    @Query("SELECT c FROM CustomerEntity c WHERE c.phone = :phone")
    CustomerEntity findByPhone(@Param("phone") String phone);

}
