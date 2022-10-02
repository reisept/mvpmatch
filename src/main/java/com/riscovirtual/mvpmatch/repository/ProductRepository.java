package com.riscovirtual.mvpmatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.riscovirtual.mvpmatch.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
