package com.epam.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
