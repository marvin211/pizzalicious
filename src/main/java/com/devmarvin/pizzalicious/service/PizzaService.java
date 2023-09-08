package com.devmarvin.pizzalicious.service;

import com.devmarvin.pizzalicious.persistence.entity.PizzaEntity;
import com.devmarvin.pizzalicious.persistence.repository.PizzaPagSortRepository;
import com.devmarvin.pizzalicious.persistence.repository.PizzaRepository;
import com.devmarvin.pizzalicious.service.dto.UpdatePizzaPriceDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;
    private final PizzaPagSortRepository pizzaPagSortRepository;

    public PizzaService(PizzaRepository pizzaRepository, PizzaPagSortRepository pizzaPagSortRepository) {//Inyectar las dependencias
        this.pizzaRepository = pizzaRepository;
        this.pizzaPagSortRepository = pizzaPagSortRepository;
    }

    //Consultar todas las pizzas, pero que la respuesta sea paginada
    public Page<PizzaEntity> getAll(Integer page, Integer sizeElements){
        Pageable pageRequest = PageRequest.of(page, sizeElements);
        return this.pizzaPagSortRepository.findAll(pageRequest);
    }

    //Obtener todas las pizzas disponibles
    public Page<PizzaEntity> getAvailable(Integer page, Integer sizeElements, String sortBy, String sortDirection){
        System.out.println("Cantidad de pizzas veganas: " + this.pizzaRepository.countByVeganTrue());
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageRequest = PageRequest.of(page, sizeElements, sort);
        return this.pizzaPagSortRepository.findByAvailableTrue(pageRequest);
    }

    //Traer una pizza disponible por su nombre
    public PizzaEntity getByName(String name){
        return this.pizzaRepository.findFirtsByAvailableTrueAndNameIgnoreCase(name)
                .orElseThrow(() -> new RuntimeException("La pizza no existe"));
    }

    //Traer un listado de pizzas disponibles que contengan o no algunos de sus ingredientes
    public List<PizzaEntity> getWhit(String ingredient){
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
    }

    //Obtener listado de pizzas disponibles que no incluyan algunos de sus ingredientes determinados
    public List<PizzaEntity> getWhitOut(String ingredient){
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient);
    }

    //Consultar las 3 pizzas mas baratas en base a un precio que se le pasa como parametro
    public List<PizzaEntity> getCheapest(Double price){
        return this.pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
    }

    //Método para obtener una pizza
    public PizzaEntity getById(Integer idPizza){
        return this.pizzaRepository.findById(idPizza)
                .orElseThrow(() -> new RuntimeException("No existe la pizza"));
    }

    //Metodo para guardar un nuevo registro
    public PizzaEntity save(PizzaEntity pizza){
        return pizzaRepository.save(pizza);
    }

    //Metodo para saber si una pizza existe
    public boolean exists(Integer idPizza){
        return this.pizzaRepository.existsById(idPizza); //true or false
    }

    //Eliminar una pizza
    public void delete(Integer idPizza){
        this.pizzaRepository.deleteById(idPizza);
    }

    @Transactional
    public void updatePrice(UpdatePizzaPriceDto dto){
        this.pizzaRepository.updatePrice(dto);
    }

}
