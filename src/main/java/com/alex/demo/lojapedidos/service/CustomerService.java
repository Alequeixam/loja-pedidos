package com.alex.demo.lojapedidos.service;

import com.alex.demo.lojapedidos.dto.CustomerDTO;
import com.alex.demo.lojapedidos.exceptions.CPFAlreadyExistsException;
import com.alex.demo.lojapedidos.exceptions.CustomerNotFoundException;
import com.alex.demo.lojapedidos.exceptions.EmailAlreadyExistsException;
import com.alex.demo.lojapedidos.model.Customer;
import com.alex.demo.lojapedidos.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer create(CustomerDTO customer) {

        List<Customer> allCustomers = repository.findAll();

        for (Customer c : allCustomers) {
            if (c.getEmail().equals(customer.email())) {
                throw new EmailAlreadyExistsException("This email is already registered.");
            }
            if (c.getCpf().equals(customer.cpf())) {
                throw new CPFAlreadyExistsException("This CPF is already registered.");
            }
        }
        var objCustomer = new Customer();
        objCustomer.setName(customer.name());
        objCustomer.setCpf(customer.cpf());
        objCustomer.setEmail(customer.email());

        return repository.save(objCustomer);
    }

    public List<CustomerDTO> getCustomers() {
        List<Customer> customers = repository.findAll();

        return customers.stream().map(customer -> mapToDTO(customer)).toList();
    }

    public CustomerDTO getCustomerById(Long id) {
        var customer = repository.findById(id).orElseThrow( () -> new CustomerNotFoundException("Customer not found."));

        return mapToDTO(customer);
    }

    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        var customer = repository.findById(id).orElseThrow( () -> new CustomerNotFoundException("Customer not found."));

        customer.setCustomerId(id);
        customer.setName(customerDTO.name());
        customer.setEmail(customerDTO.email());
        customer.setCpf(customerDTO.cpf());

        Customer savedCustomer = repository.save(customer);
        return mapToDTO(savedCustomer);
    }


    public CustomerDTO mapToDTO(Customer customer) {
        return new CustomerDTO(customer.getCustomerId(), customer.getName(), customer.getEmail(), customer.getCpf());
    }
    public Customer mapToEntity(CustomerDTO dto) {
        var customer = new Customer();
        customer.setCustomerId(dto.customerId());
        customer.setName(dto.name());
        customer.setEmail(dto.email());
        customer.setCpf(dto.cpf());
        return customer;
    }
}
