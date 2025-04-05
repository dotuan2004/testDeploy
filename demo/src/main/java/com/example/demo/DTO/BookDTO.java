package com.example.demo.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private Integer bookId;
    private String bookName;
    private String author;
    private Double salePrice;
}
