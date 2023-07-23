package com.demo.service.impl;

import com.demo.model.Category;
import com.demo.model.Product;
import com.demo.model.ProductDto;
import com.demo.model.Student;
import com.demo.repository.ProductRepository;
import com.demo.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    @PersistenceUnit
    private final EntityManagerFactory factory;
    @Autowired
    private ProductRepository productRepository;
    private EntityManager entityManager;
    private EntityTransaction transaction;

    public ProductServiceImpl(EntityManagerFactory factory) {
        this.factory = factory;
        entityManager = factory.createEntityManager();
    }

    @Override
    public List<ProductDto> getProductDto() {
//        Product product = entityManager.find(Product.class, 1);
//        Category category = entityManager.find(Category.class, product.getCid());
//        ProductDto dto = new ProductDto();
//        dto.setCid(product.getCid());
//        dto.setCategory(category);
//        dto.setPname(product.getPname());
//        dto.setPid(product.getPid());
//        List<ProductDto> dtos = new ArrayList<>();
//        dtos.add(dto);
        return null;
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void delete(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product findByID(int id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()){
            throw new RuntimeException();
        }
        return productOptional.get();
    }

    @Override
    public List<Product> findByName(String name) {
        String query = "SELECT * FROM product WHERE pname = ?1";
        List<Product> list = entityManager.createNativeQuery(query, Product.class).setParameter(1,name).getResultList();
        return productRepository.findAllByPname(name);
    }

    @Override
    public List<Product> findByCategory(int cid) {
        String query = "SELECT * FROM category as c, product as p WHERE c.cid = p.cid";
        List<Product> list = entityManager.createNativeQuery(query, Category.class).getResultList();
        return productRepository.findAllByCid(cid);
    }

    @Override
    public List<ProductDto> getInfor() {
        List<Map<String, Objects>> mapList = productRepository.findInfor();
        ObjectMapper mapper = new ObjectMapper();
        List<ProductDto> productDtos = new ArrayList<>();
        for (Map m:mapList) {
            ProductDto dto = mapper.convertValue(m, ProductDto.class);
            productDtos.add(dto);
        }
        return productDtos;
    }
}
