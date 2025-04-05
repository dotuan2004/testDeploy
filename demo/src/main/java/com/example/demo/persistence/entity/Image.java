package com.example.demo.persistence.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Integer imageId;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "icon")
    private Boolean icon;

    @Column(name = "link")
    private String link;

    @Column(name = "image_data", columnDefinition = "LONGTEXT")
    @Lob
    private String imageData;

    @ManyToOne(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}
    )
    @JoinColumn(name = "book_id")
    private Book book;

    // Getters and Setters
}
