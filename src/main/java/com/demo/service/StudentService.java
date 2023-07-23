package com.demo.service;

import com.demo.model.Customer;
import com.demo.model.Student;
import com.demo.model.StudentDto;

import java.util.ArrayList;
import java.util.List;

public interface StudentService {
    ArrayList<StudentDto> read();

    void create(Student student);

    Student save(Student student);

    void update(Student student);

    void delete(int id);

    List<Student> findByAID(int aid);

    Student findByID(int id);
}
