package com.example.demo.persistence.entity;

    import jakarta.persistence.Entity;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import jakarta.persistence.*;
    import java.util.List;
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    @Table(name = "category")
    public class Category {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "category_id")
        private Integer categoryId;

        @Column(name = "category_name")
        private String categoryName;

        @ManyToMany(fetch = FetchType.LAZY, cascade = {
                CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
        @JoinTable(
                name = "book_category",
                joinColumns = @JoinColumn(name = "category_id"),
                inverseJoinColumns = @JoinColumn(name = "book_id")
        )
        private List<Book> books;

        // Getters and Setters
    }
