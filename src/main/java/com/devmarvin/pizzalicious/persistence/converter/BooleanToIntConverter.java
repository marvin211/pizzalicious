
package com.devmarvin.pizzalicious.persistence.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class BooleanToIntConverter implements AttributeConverter<Boolean, Integer> { //Convierte un atributo de tipo Boolean a un tipo Integer para su almacenamiento en la base de datos y viceversa.

    @Override
    public Integer convertToDatabaseColumn(Boolean attribute) {//Convertir de boolean a integer
        return (attribute != null && attribute) ? 1 : 0;

    }

    @Override
    public Boolean convertToEntityAttribute(Integer dbData) { //Convertir de integer a boolean
        return (dbData != null && dbData == 1);
    }
}
