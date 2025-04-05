package com.example.demo.persistence.Repository;

import com.example.demo.persistence.entity.DeliveryMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "hinh-thuc-giao-hang")
public interface DeliveryMethodRepository extends JpaRepository<DeliveryMethod, Integer> {
}
