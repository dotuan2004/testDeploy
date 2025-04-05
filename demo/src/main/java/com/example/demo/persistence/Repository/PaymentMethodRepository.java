package com.example.demo.persistence.Repository;

import com.example.demo.persistence.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "hinh-thuc-thanh-toan")
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer> {
}
