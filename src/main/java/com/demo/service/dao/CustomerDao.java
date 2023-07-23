package com.demo.service.dao;

import com.demo.conn.ConnectJdbc;
import com.demo.model.Customer;
import com.demo.service.CustomerService;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

@Service("svDao")
public class CustomerDao implements CustomerService {
    private PreparedStatement preparedStatement;

    @Override
    public ArrayList<Customer> read() {
        ArrayList<Customer> arrayList = new ArrayList<>();
        try {
            preparedStatement = ConnectJdbc.conn().prepareStatement("SELECT * FROM customers");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int cusId = resultSet.getInt(1);
                String cusName = resultSet.getString(2);
                String birthDay = resultSet.getString(3);
                String address = resultSet.getString(4);
                String phone = resultSet.getString(5);
                Customer customer = new Customer(cusId, cusName, birthDay, phone, 1);
                arrayList.add(customer);
            }
            return arrayList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Customer customers) {
        try {
            int randomNum = (int) (Math.random() * (100000 - 1));
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(simpleDateFormat.parse(customers.getBirthDay()).getTime());
            preparedStatement = ConnectJdbc.conn().prepareStatement("INSERT INTO customers VALUES (?,?,?,?,?)");
            preparedStatement.setInt(1, randomNum);
            preparedStatement.setString(2, customers.getCusName());
            preparedStatement.setString(3, customers.getBirthDay());
            preparedStatement.setString(4, "Hà Nội");
            preparedStatement.setString(5, customers.getPhone());
            preparedStatement.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Customer customers) {
        try {
            preparedStatement = ConnectJdbc.conn().prepareStatement("UPDATE customers VALUES (?,?,?,?)");

            preparedStatement.setString(2, customers.getCusName());
            preparedStatement.setString(3, customers.getBirthDay());
            preparedStatement.setString(4, "Hà Nội");
            preparedStatement.setString(5, customers.getPhone());
            preparedStatement.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void checkID(Customer customer) {

    }

    @Override
    public boolean checkCId(int cId) {
        return false;
    }

    @Override
    public Customer findByID(int id) {
        return null;
    }
}
