package com.epam.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
