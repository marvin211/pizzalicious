package com.devmarvin.pizzalicious.persistence.entity;

import com.devmarvin.pizzalicious.persistence.audit.AuditPizzaListener;
import com.devmarvin.pizzalicious.persistence.audit.AuditableEntity;
import com.devmarvin.pizzalicious.persistence.converter.BooleanToIntConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@Entity
@Table(name = "pizza")
@EntityListeners({AuditingEntityListener.class, AuditPizzaListener.class})
@Getter
@Setter
@NoArgsConstructor
public class PizzaEntity extends AuditableEntity implements Serializable { //Se extiende de AuditableEntity para que herede las columnas de fecha de creación y fecha de modificación para auditar

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pizza", nullable = false)
    private Integer idPizza;

    @Column(nullable = false, length = 30, unique = true)
    private String name;

    @Column(nullable = false, length = 150)
    private String description;

    @Column(nullable = false, columnDefinition = "Decimal(5,2)")
    private Double price;

    @Column(columnDefinition = "Smallint")
    @Convert(converter = BooleanToIntConverter.class)
    private Boolean vegetarian;

    @Column(columnDefinition = "Smallint")
    @Convert(converter = BooleanToIntConverter.class)
    private Boolean vegan;

    @Column(columnDefinition = "Smallint", nullable = false)
    @Convert(converter = BooleanToIntConverter.class)
    private Boolean available;

    @Override
    public String toString() {
        return "PizzaEntity{" +
                "idPizza=" + idPizza +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", vegetarian=" + vegetarian +
                ", vegan=" + vegan +
                ", available=" + available +
                '}';
    }

}
