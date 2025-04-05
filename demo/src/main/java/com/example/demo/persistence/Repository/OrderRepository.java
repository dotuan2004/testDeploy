package com.example.demo.persistence.Repository;


import com.example.demo.persistence.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "don-hang")
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
