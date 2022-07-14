package com.microservice.hibernate.repository;

import com.microservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
}

