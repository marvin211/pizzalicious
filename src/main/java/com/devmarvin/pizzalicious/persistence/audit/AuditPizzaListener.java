package com.devmarvin.pizzalicious.persistence.audit;

import com.devmarvin.pizzalicious.persistence.entity.PizzaEntity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import org.springframework.util.SerializationUtils;

//AuditPizzaListener tiene que se ejecutan automaticamente cuando se realizan operaciones CRUD
public class AuditPizzaListener {

    private PizzaEntity currentValue;

    //Se va ejecutar despues de que ocurra un select
    @PostLoad
    public void postLoad(PizzaEntity entity){
        System.out.println("POST LOAD");
        this.currentValue  = SerializationUtils.clone(entity);
    }

    //Despues de que guarde o actualice una pizza se ejecutara este metodo
    @PostPersist
    @PostUpdate
    public void onPostPersist(PizzaEntity entity){
        System.out.println("POST PERSIST OR UPDATE");
        System.out.println("OLD VALUE: " + currentValue);
        System.out.println("NEW VALUE: " + entity.toString());
    }

    //Antes de que se elimine una pizza se ejecutara este método
    @PreRemove
    public void onPreDelete(PizzaEntity entity){
        System.out.println(entity.toString());
    }

}
