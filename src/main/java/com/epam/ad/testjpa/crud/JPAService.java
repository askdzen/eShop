package com.epam.ad.testjpa.crud;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.*;
import java.util.List;

@Stateless
@Named
public class JPAService<T>  {
@Inject
    private EntityManager em;

    private List<T>listAll;

    public Class<T> entityClassName;

    public JPAService() {
    }

    public JPAService(Class<T> entityClassName) {
        this.entityClassName = entityClassName;
    }

    public T add(T userEntity){

        T entityFromDB = em.merge(userEntity);
        return entityFromDB;
    }


    public void delete(int id){
        em.remove(get(id));

    }

    public T get(int id){
        return em.find(entityClassName, id);
    }

    public void update(T userEntity){
        em.merge(userEntity);

    }


    public List<T> getAll(){

        TypedQuery<T> namedQuery = em.createNamedQuery(entityClassName.getSimpleName()+".getAll", entityClassName);
        listAll=namedQuery.getResultList();
        return listAll;
    }

    public T getById(int id,String paramName){

        return (T) em.createNamedQuery(entityClassName.getSimpleName()+".findById").setParameter(paramName, id).getSingleResult();
    }

    public EntityManager getEm() {
        return em;
    }
}
