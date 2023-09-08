
package com.devmarvin.pizzalicious.persistence.projection;

import java.time.LocalDateTime;

// Esta interfaz se va a usar en el OrderRepository para definir el método que va a ejecutar el query personalizado
public interface OrderSummary {

    Integer getIdOrder();
    String getCustomerName();
    LocalDateTime getOrderDate();
    Double getOrderTotal();
    String getPizzaNames();

}
