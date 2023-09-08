package com.devmarvin.pizzalicious.persistence.repository;

import com.devmarvin.pizzalicious.persistence.entity.PizzaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface PizzaPagSortRepository extends ListPagingAndSortingRepository<PizzaEntity, Integer> {

    //Consultar todas las pizzas disponibles, usando el ListPagingAndSortingRepository
    Page<PizzaEntity> findByAvailableTrue(Pageable pageable);
}
