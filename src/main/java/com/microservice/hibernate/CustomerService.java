package com.microservice.hibernate;

import com.microservice.hibernate.repository.CustomerRepository;
import com.microservice.hibernate.repository.CustomerRepositoryCustom;
import com.microservice.entity.Customer;
import com.microservice.hibernate.dto.CustomerData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service("customerService")
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerRepositoryCustom customerRepositoryCustom;


    /**
     * Create a customer based on the data sent to the service class.
     * @param customer
     * @return DTO representation of the customer
     */
    public CustomerData saveCustomer(CustomerData customer) {
        Customer customerModel = populateCustomerEntity(customer);
        return populateCustomerData(customerRepository.save(customerModel));
    }

    /**
     * Delete customer based on the customer ID.We can also use other option to delete customer
     * based on the entity (passing JPA entity class as method parameter)
     * @param customerId
     * @return boolean flag showing the request status
     */
    public boolean deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
        return true;
    }

    /**
     * Delete customer based on the customer name.We can also use other option to delete customer
     * based on the entity (passing JPA entity class as method parameter)
     * @param firstName
     * @return boolean flag showing the request status
     */
    public boolean deleteCustomerByFirstName(String firstName) {
        customerRepositoryCustom.deleteByFirstName(firstName);
        return true;
    }

    /**
     * Method to return the list of all the customers in the system.This is a simple
     * implementation but use pagination in the real world example.
     * @return list of customer
     */
    public List < CustomerData > getAllCustomers() {
        List < CustomerData > customers = new ArrayList < > ();
        List < Customer > customerList = customerRepository.findAll();
        customerList.forEach(customer -> {
                customers.add(populateCustomerData(customer));
        });
        return customers;
    }

    /**
     * Get customer by ID. The service will send the customer data else will throw the exception.
     * @param customerId
     * @return CustomerData.java
     */
    public CustomerData getCustomerById(Long customerId) {
        return populateCustomerData(customerRepository.findById(customerId).orElseThrow(() -> new EntityNotFoundException("Customer not found")));
    }

    /**
     * Get customer by name. The service will send the customer data else will throw the exception.
     * @param firstName
     * @return CustomerData.java
     */
    public CustomerData getCustomerByFirstName(String firstName) {
        return populateCustomerData(customerRepositoryCustom.findByFirstName(firstName));
    }



    /**
     * Internal method to convert Customer JPA entity to the DTO object
     * for frontend data
     * @param customer
     * @return CustomerData.java
     */
    private CustomerData populateCustomerData(final Customer customer) {
        CustomerData customerData = new CustomerData();
        customerData.setId(customer.getId());
        customerData.setFirstName(customer.getFirstName());
        customerData.setLastName(customer.getLastName());
        customerData.setEmail(customer.getEmail());
        return customerData;
    }

    /**
     * Method to map the front end customer object to the JPA customer entity.
     * @param customerData
     * @return Customer
     */
    private Customer populateCustomerEntity(CustomerData customerData) {
        Customer customer = new Customer();
        customer.setFirstName(customerData.getFirstName());
        customer.setLastName(customerData.getLastName());
        customer.setEmail(customerData.getEmail());
        return customer;
    }
}