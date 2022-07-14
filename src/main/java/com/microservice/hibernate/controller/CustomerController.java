package com.microservice.hibernate.controller;

import com.microservice.hibernate.dto.CustomerData;
import com.microservice.hibernate.CustomerService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Resource(name = "customerService")
    private CustomerService customerService;

    /**
     * <p>Get all customer data in the system.For production system you many want to use
     * pagination.</p>
     * @return List<CustomerData>
     */
    @GetMapping
    public List < CustomerData > getCustomers() {
        return customerService.getAllCustomers();
    }

    /**
     * Method to get the customer data based on the ID.
     * @param firstName
     * @return CustomerData
     */
    @GetMapping("/customername/{firstName}")
    public CustomerData getCustomerByFirstName(@PathVariable String firstName) {
        return customerService.getCustomerByFirstName(firstName);
    }

    /**
     * Method to get the customer data based on the ID.
     * @param id
     * @return CustomerData
     */
    @GetMapping("/customer/{id:\\d+}")
    public CustomerData getCustomer(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    /**
     * Post request to create customer information int the system.
     * @param customerData
     * @return
     */
    @PostMapping("/customer")
    public CustomerData saveCustomer(final @RequestBody CustomerData customerData) {
        return customerService.saveCustomer(customerData);
    }

    /**
     * <p>Delete customer from the system based on the ID. The method mapping is like the getCustomer with difference of
     * @DeleteMapping and @GetMapping</p>
     * @param id
     * @return
     */
    @DeleteMapping("/customer/{id:\\d+}")
    public Boolean deleteCustomer(@PathVariable Long id) {
        return customerService.deleteCustomer(id);
    }


    /**
     * <p>Delete customer from the system based on the name. The method mapping is like the getCustomer with difference of
     * @DeleteMapping and @GetMapping</p>
     * @param firstName
     * @return
     */
    @DeleteMapping("/customername/{firstName}")
    public Boolean deleteCustomerByFirstName(@PathVariable String firstName) {
        return customerService.deleteCustomerByFirstName(firstName);
    }

}