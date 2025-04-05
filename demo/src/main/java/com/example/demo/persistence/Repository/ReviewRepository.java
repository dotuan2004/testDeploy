package com.example.demo.persistence.Repository;

import com.example.demo.persistence.entity.Review;
import com.example.demo.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RepositoryRestResource(path ="su-danh-gia")

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    public List<Review> findByBook_BookId(@RequestParam("maSach") Integer maSach);

}
