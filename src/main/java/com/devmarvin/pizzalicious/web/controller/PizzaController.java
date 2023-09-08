package com.devmarvin.pizzalicious.web.controller;

import com.devmarvin.pizzalicious.persistence.entity.PizzaEntity;
import com.devmarvin.pizzalicious.service.PizzaService;
import com.devmarvin.pizzalicious.service.dto.UpdatePizzaPriceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {

    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    //Obtener todas la pizzas
    @GetMapping
    public ResponseEntity<Page<PizzaEntity>> getAll(@RequestParam(defaultValue = "0") Integer page,
                                                    @RequestParam(defaultValue = "5") Integer sizeElements){
        return ResponseEntity.ok(this.pizzaService.getAll(page, sizeElements));
    }

    //Obtener la pizzas disponibles
    @GetMapping("/available")
    public ResponseEntity<Page<PizzaEntity>> getAllAvailable(@RequestParam(defaultValue = "0") Integer page,
                                                             @RequestParam(defaultValue = "5") Integer sizeElements,
                                                             @RequestParam(defaultValue = "price") String order,
                                                             @RequestParam(defaultValue = "ASC") String sortDirection){
        return ResponseEntity.ok(this.pizzaService.getAvailable(page, sizeElements, order, sortDirection));
    }

    //Obtener una pizza disponible por su nombre
    @GetMapping("/name/{name}")
    public ResponseEntity<PizzaEntity> getPizzaByName(@PathVariable String name){
        return ResponseEntity.ok(this.pizzaService.getByName(name));
    }

    //Obtener listado de pizzas disponibles que contengan o no algunos de sus ingredientes
    @GetMapping("/with/{ingredient}")
    public ResponseEntity<List<PizzaEntity>> getWhit(@PathVariable String ingredient){
        return ResponseEntity.ok(this.pizzaService.getWhit(ingredient));
    }

    //Obtener listado de pizzas disponibles que no incluyan alguno de sus ingredientes
    @GetMapping("/without/{ingredient}")
    public ResponseEntity<List<PizzaEntity>> getWhitOut(@PathVariable String ingredient){
        return ResponseEntity.ok(this.pizzaService.getWhitOut(ingredient));
    }

    //Obtener una pizza
    @GetMapping("/{idPizza}")
    public ResponseEntity<PizzaEntity> getPizzaById(@PathVariable Integer idPizza){
        return ResponseEntity.ok(this.pizzaService.getById(idPizza));
    }

    //Obtener las 3 pizzas mas baratas, en base a un precio que se le pasa como parametro
    @GetMapping("/cheapest/{price}")
    public ResponseEntity<List<PizzaEntity>> getCheapestPizzas(@PathVariable Double price){
        return ResponseEntity.ok(this.pizzaService.getCheapest(price));
    }

    //Guardar una nueva pizza
    @PostMapping
    public ResponseEntity<PizzaEntity> savePizza(@RequestBody PizzaEntity pizza){

        if(pizza.getIdPizza() == null || !this.pizzaService.exists(pizza.getIdPizza())){
            return ResponseEntity.ok(this.pizzaService.save(pizza));
        }

        //Si por el contrario la pizza ya existe
        return ResponseEntity.badRequest().build(); //Error un badRequest para que no se procese esta petición
    }

    //Actualizamos una pizza
    @PutMapping
    public ResponseEntity<PizzaEntity> updatePizza(@RequestBody PizzaEntity pizza){
        if(pizza.getIdPizza() != null && this.pizzaService.exists(pizza.getIdPizza())){
            return ResponseEntity.ok(this.pizzaService.save(pizza));
        }

        return ResponseEntity.badRequest().build(); //Error badRequest para que no se procese esta petición.
    }

    //Eliminar una pizza
    @DeleteMapping("/{idPizza}")
    public ResponseEntity<Void> deletePizza(@PathVariable Integer idPizza){

        if(this.pizzaService.exists(idPizza)){
            this.pizzaService.delete(idPizza);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }

    //Actualizar el precio de una pizza
    @PutMapping("/price")
    public ResponseEntity<Void> updatePizzaPrice(@RequestBody UpdatePizzaPriceDto updatePizzaPriceDto){
        if(this.pizzaService.exists(updatePizzaPriceDto.getPizzaId())){
            this.pizzaService.updatePrice(updatePizzaPriceDto);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }

}
