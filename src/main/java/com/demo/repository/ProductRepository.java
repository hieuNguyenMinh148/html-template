package com.demo.repository;

import com.demo.model.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByCid(int cid);
    List<Product> findAllByPname(String pname);

//    @Modifying
//    @Transactional
//    @Query(value = "INSERT INTO  product (pid,cid,pname) values(:pid,:cid,:pname)",nativeQuery = true)
//    void insertProduct(@Param("id") int pid,@Param("cid")  int cid,@Param("pname")  String pname);
    @Query(value = "SELECT p.*, c.name FROM product as p, category as c WHERE c.cid = p.cid",nativeQuery = true)
    List<Map<String, Objects>> findInfor();



}
