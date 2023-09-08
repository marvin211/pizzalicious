package com.devmarvin.pizzalicious.persistence.repository;

import com.devmarvin.pizzalicious.persistence.entity.PizzaEntity;
import com.devmarvin.pizzalicious.service.dto.UpdatePizzaPriceDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {

    List<PizzaEntity> findAllByAvailableTrueOrderByPrice();//Buscar todas las pizzas disponibles y ordenarlas por precio

    //Traer una pizza disponible a través de su nombre
    Optional<PizzaEntity> findFirtsByAvailableTrueAndNameIgnoreCase(String name);

    //Recuperar las pizzas disponibles que tengan o no alguno de sus ingredientes
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String ingredient);

    //Buscar todas las pizzas disponbiles que no incluyan alguno de sus ingredientes determinados
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String ingredient);

    //Cuenta todas las pizzas disponibles cuyo atributo vegan sea verdadero
    Integer countByVeganTrue();

    //Consultar la pizzas mas baratas
    List<PizzaEntity> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(Double price);

    //Actualizar el precio de una pizza directamente desde un sql nativo
    @Modifying
    @Query(
            value = "UPDATE pizza SET price = :#{#newPizzaPrice.newPrice} " +
                    "WHERE id_pizza = :#{#newPizzaPrice.pizzaId}",
            nativeQuery = true)
    void updatePrice(@Param("newPizzaPrice") UpdatePizzaPriceDto newPizzaPrice);

}
