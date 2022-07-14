package com.microservice.hibernate.repository;

import com.microservice.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

    @Autowired
    @Lazy
    CustomerRepository customerRepository;

    @PersistenceContext
    private EntityManager em;

    public Customer findByFirstName(String firstName) {
        TypedQuery query = em.createQuery("select c from Customer c where c.firstName = ?1", Customer.class);
        query.setParameter(1, firstName);

        return (Customer) query.getResultList().get(0);
    }

    @Transactional
    public void deleteByFirstName(String firstName) {
        Query query = em.createQuery("delete from Customer c where c.firstName = ?1");
        query.setParameter(1, firstName);

        query.executeUpdate();

    }
}
