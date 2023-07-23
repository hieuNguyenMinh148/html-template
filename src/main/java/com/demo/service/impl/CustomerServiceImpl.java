package com.demo.service.impl;

import com.demo.model.Customer;
import com.demo.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("svImpl")
public class CustomerServiceImpl implements CustomerService {
    ArrayList<Customer> customers = new ArrayList<>();

    public void checkList() {
        if (customers.isEmpty()) {
            customers.add(new Customer(1, "asdh", "asdjajd", "asdjh", 2));
            customers.add(new Customer(2, "123", "12345", "wq", 3));
            customers.add(new Customer(3, "567", "6786", "986", 4));
        }
    }

    @Override
    public ArrayList<Customer> read() {
        checkList();
        return customers;
    }

    @Override
    public void create(Customer customers) {
        checkList();
        customers.setCusId(this.customers.size() + 1);
        this.customers.add(customers);
    }

    @Override
    public void update(Customer customers) {
        for (Customer c : this.customers) {
            if (c.getCusId() == customers.getCusId()) {
                c.setCusName(customers.getCusName());
                c.setBirthDay(customers.getBirthDay());
                c.setPhone(customers.getPhone());
                c.setaId(customers.getaId());
                break;
            }
        }
    }

    @Override
    public void delete(int id) {
        for (int i = 0; i < customers.size(); i++) {
            if (id == customers.get(i).getCusId()) {
                customers.remove(i);
                break;
            }
        }
    }

    @Override
    public void checkID(Customer customer) {
        if (customer.getCusId() == 0) {
            create(customer);
        } else {
            update(customer);
        }
    }

    @Override
    public boolean checkCId(int cId) {
        return false;
    }

    @Override
    public Customer findByID(int id) {
        checkList();
        for (Customer c : customers) {
            if (c.getCusId() == id) {
                return c;
            }
        }
        return null;
    }
}
