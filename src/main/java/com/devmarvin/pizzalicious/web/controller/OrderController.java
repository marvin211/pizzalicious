package com.devmarvin.pizzalicious.web.controller;

import com.devmarvin.pizzalicious.persistence.entity.OrderEntity;
import com.devmarvin.pizzalicious.persistence.projection.OrderSummary;
import com.devmarvin.pizzalicious.service.OrderService;
import com.devmarvin.pizzalicious.service.dto.RandomOrderDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderEntity>> getAllOrders(){
        return ResponseEntity.ok(this.orderService.getAll());
    }


    //Obtener todas la ordenes que ha tenido la pizzeria el dia actual
    @GetMapping("/today")
    public ResponseEntity<List<OrderEntity>> getTodayOrder(){
        return ResponseEntity.ok(this.orderService.getTodayOrders());
    }

    //Obtener todas las ordenes que han sido por domicilio o que han pasado por ellas
    @GetMapping("/outside")
    public ResponseEntity<List<OrderEntity>> getOutsideOrders(){
        return ResponseEntity.ok(this.orderService.getOutsideOrders());
    }

    //Obtener todas las ordenes que ha tenido un cliente en especifico
    @GetMapping("/customer/{idCustomer}")
    public ResponseEntity<List<OrderEntity>> getCustomerOrders(@PathVariable String idCustomer){
        return ResponseEntity.ok(this.orderService.getCustomerOrders(idCustomer));
    }

    //Obtener el id de la orden, el nombre del cliente, el total de la orden, la fecha de la orden y el nombre de la pizzas
    @GetMapping("/summary/{idOrder}")
    public ResponseEntity<OrderSummary> getSummary(@PathVariable Integer idOrder){
        return ResponseEntity.ok(this.orderService.getSummary(idOrder));
    }

    //Seleccionar aleatoriamente una pizza disponible, aplica un descuento.
    @PostMapping("/random")
    public ResponseEntity<Boolean> getRandomOrder(@RequestBody RandomOrderDto randomOrderDto){
        return ResponseEntity.ok(this.orderService.saveRandomOrder(randomOrderDto));
    }

}
