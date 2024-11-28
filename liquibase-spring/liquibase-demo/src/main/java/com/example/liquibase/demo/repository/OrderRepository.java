package com.example.liquibase.demo.repository;

import com.example.liquibase.demo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
