package com.sandeep.multipledbtest.mysql.repo;

import com.sandeep.multipledbtest.mysql.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
}
