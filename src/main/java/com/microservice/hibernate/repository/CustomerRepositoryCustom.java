package com.microservice.hibernate.repository;

import com.microservice.entity.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepositoryCustom {
    public Customer findByFirstName(String firstName
    );

    public void deleteByFirstName(String firstName);
}

