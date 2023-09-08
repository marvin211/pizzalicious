package com.devmarvin.pizzalicious.service;

import com.devmarvin.pizzalicious.persistence.entity.OrderEntity;
import com.devmarvin.pizzalicious.persistence.projection.OrderSummary;
import com.devmarvin.pizzalicious.persistence.repository.OrderRepository;
import com.devmarvin.pizzalicious.service.dto.RandomOrderDto;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {

    private static final String DELIVERY = "D";
    private static final String CARRYOUT = "C";
    private static final String ON_SITE = "S";

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){ //Inyectamos el repositorio a través del constructor
        this.orderRepository = orderRepository;
    }

    //Recuperar todas las ordenes de la pizzeria
    public List<OrderEntity> getAll(){

        List<OrderEntity> orders = this.orderRepository.findAll();

        return orders;
    }

    //Obtener todas la ordenes que ha tenido la pizzeria el dia de hoy
    public List<OrderEntity> getTodayOrders(){
        LocalDateTime today = LocalDate.now().atTime(0, 0); //Obtenemos la fecha actual
        return this.orderRepository.findAllByDateAfter(today);
    }

    //Obtener todas las ordenes que han sido por domicilio
    public List<OrderEntity> getOutsideOrders(){
        List<String> methods = Arrays.asList(DELIVERY,CARRYOUT);
        return this.orderRepository.findAllByMethodIn(methods);
    }

    //Consultar las ordenes que ha tenido un cliente en especifico
    @Secured("ROLE_ADMIN") //Los usuarios con el rol de administrador pueden usar este método
    public List<OrderEntity> getCustomerOrders(String idCustomer){
        return this.orderRepository.findCustomerOrders(idCustomer);
    }

    //Obtener id de la orden, el nombre del cliente, el total de la orden, la fecha de la orden y el nombre de la pizzas
    public OrderSummary getSummary(Integer idOrder){
        return this.orderRepository.findSummary(idOrder);
    }

    //Llamar la función que selecciona aleatoriamente una pizza disponible, aplica un descuento.
    @Transactional
    public boolean saveRandomOrder(RandomOrderDto randomOrderDto){
        return this.orderRepository.saveRandomOrder(randomOrderDto.getIdCustomer(), randomOrderDto.getMethod());
    }

}
