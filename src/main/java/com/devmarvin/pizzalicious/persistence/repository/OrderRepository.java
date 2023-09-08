package com.devmarvin.pizzalicious.persistence.repository;

import com.devmarvin.pizzalicious.persistence.entity.OrderEntity;

import java.time.LocalDateTime;
import java.util.List;

import com.devmarvin.pizzalicious.persistence.projection.OrderSummary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {

    //Recuperar la ordenes que ha tenido la pizzeria el dia actual
    List<OrderEntity> findAllByDateAfter(LocalDateTime date);

    //Ver las ordenes que han sindo por domicilio
    List<OrderEntity> findAllByMethodIn(List<String> methods);

    //Consultar las ordenes que ha tenido un cliente en especifico, usando sql nativo
    @Query(value = "SELECT * FROM pizza_order WHERE id_customer = :id", nativeQuery = true)
    List<OrderEntity> findCustomerOrders(@Param("id") String idCustomer);

    //Obtener el id de la orden, el nombre del cliente, la fecha de la orden, el total de la orden y el nombre de las pizzas que tiene esa orden
    @Query(value = "SELECT po.id_order AS idOrder, cu.name AS customerName, po.date AS orderDate, " +
            "po.total AS orderTotal, string_agg(pi.name, ', ') AS pizzaNames " +
            "FROM pizza_order po " +
            "    INNER JOIN customer cu ON po.id_customer = cu.id_customer " +
            "    INNER JOIN order_item oi ON po.id_order = oi.id_order " +
            "    INNER JOIN pizza pi ON oi.id_pizza = pi.id_pizza " +
            "WHERE po.id_order = :orderId " +
            "GROUP BY po.id_order, cu.name, po.date, po.total;", nativeQuery = true)
    OrderSummary findSummary(@Param("orderId") Integer orderId);

    //Funcion que me permite tomar un pedido de pizza aleatorio
    @Query(value = "SELECT take_random_pizza_order(:id_customer, :method) as order_taken", nativeQuery = true) //Los parametros :id_customer y :method deben nombrarse igual que en el procedimiento almacenado es decir hay que respetar el nombre de los parametros con los que se llaman en el procedimiento almacenado en el procedimiento almacenado esos parametros se llaman igual que en la respectivas columnas de la tabla de la base de datos
    boolean saveRandomOrder(@Param("id_customer") String idCustomer, @Param("method") String method); //Retorna un booleano, si se tomo o no el pedido de pizza aleatorio

}
