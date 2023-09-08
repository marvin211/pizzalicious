
package com.devmarvin.pizzalicious.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemId implements Serializable { //Clase auxiliar que representa la clave primaria compuesta de la clase OrderItem.
    private Integer idItem;
    private Integer idOrder;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItemId that)) return false;
        return Objects.equals(idItem, that.idItem) && Objects.equals(idOrder, that.idOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idItem, idOrder);
    }
}
