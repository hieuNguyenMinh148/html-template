package com.demo.service.impl;

import com.demo.model.Address;
import com.demo.model.Customer;
import com.demo.model.Student;
import com.demo.model.StudentDto;
import com.demo.repository.StudentRepository;
import com.demo.service.StudentService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @PersistenceUnit
    private EntityManagerFactory factory;
    private EntityManager entityManager;
    private EntityTransaction transaction;

    public StudentServiceImpl(EntityManagerFactory factory) {
        this.factory = factory;
        entityManager = factory.createEntityManager();
    }

    @Override
    public ArrayList<StudentDto> read() {
        String query = "SELECT s.*, a.province FROM student as s, address as a WHERE s.aid = a.aid";
        ArrayList<StudentDto> list = (ArrayList<StudentDto>) entityManager.createNativeQuery(query, StudentDto.class).getResultList();
        return list;
    }

    @Override
    public void create(Student student) {
        if (student.getSid() > 0) {
            update(student);
        } else {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(student);
            transaction.commit();
        }

    }

    @Autowired
    private StudentRepository repository;

    @Override
    public Student save(Student student) {
        return repository.save(student);
    }

    @Override
    public void update(Student student) {
        transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(student);
        transaction.commit();
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Student> findByAID(int aid) {
        String queryHQL = "SELECT s FROM Student as s WHERE address.aid = ?1";
        String querySQL = "SELECT * FROM student WHERE aid = ?1";
//        List<Student> list = entityManager.createQuery(queryHQL,Student.class).setParameter(1,aid).getResultList();
        List<Student> list2 = entityManager.createNativeQuery(querySQL, Student.class).setParameter(1, aid).getResultList();
        return list2;
    }

    @Override
    public Student findByID(int id) {
        Student student = entityManager.find(Student.class, id);
        return student;
    }
}
