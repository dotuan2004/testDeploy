package com.example.demo.persistence.Repository;


import com.example.demo.persistence.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "hinh-anh")
public interface ImageRepository extends JpaRepository<Image,Integer> {
}
