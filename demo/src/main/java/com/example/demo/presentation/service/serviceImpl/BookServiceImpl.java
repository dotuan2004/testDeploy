package com.example.demo.presentation.service.serviceImpl;

import ch.qos.logback.core.util.StringUtil;
import com.example.demo.persistence.Repository.BookRepository;
import com.example.demo.persistence.Repository.ImageRepository;
import com.example.demo.persistence.entity.Book;
import com.example.demo.persistence.entity.Image;
import com.example.demo.presentation.model.request.Book.PostCreateBookRequest;
import com.example.demo.presentation.model.request.Book.PostDeleteBookRequest;
import com.example.demo.presentation.model.request.Book.PostUpdateBookRequest;
import com.example.demo.presentation.model.response.BaseResponse;
import com.example.demo.presentation.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    ImageRepository imageRepository;

    @Override
    public BaseResponse<?> addBook(PostCreateBookRequest request) {
        Book book=Book.builder().bookName(request.getBookName())
                .author(request.getAuthor())
                .ISBN(request.getIsBn())
                .description(request.getDescription())
                .quantity(request.getQuantity())
                .listPrice(request.getListPrice())
                .salePrice(request.getSalePrice())
                .build();
        bookRepository.save(book);
        if(!StringUtil.isNullOrEmpty(request.getImageData())){
            Image image = new Image();
            image.setImageData(request.getImageData());
            image.setBook(book);
            imageRepository.save(image);
        }
        return new BaseResponse<>();
    }

    public BaseResponse<?> updateBook( PostUpdateBookRequest request) {
        // Tìm sách theo id
        Optional<Book> optionalBook = bookRepository.findById(request.getBookId());

        if (!optionalBook.isPresent()) {
            return null;
        }
        Book book = optionalBook.get();
        book.setBookName(request.getBookName());
        book.setAuthor(request.getAuthor());
        book.setISBN(request.getIsBn());
        book.setDescription(request.getDescription());
        book.setListPrice(request.getListPrice());
        book.setSalePrice(request.getSalePrice());
        book.setQuantity(request.getQuantity());
        bookRepository.save(book);
        if (!StringUtil.isNullOrEmpty(request.getImageData())) {
            List<Image> existingImages = book.getImages();
            if (existingImages != null) {
                existingImages.clear();
            }
            Image image = new Image();
            image.setImageData(request.getImageData());
            image.setBook(book);
            imageRepository.save(image);
        }
        return new BaseResponse<>();
    }
    public BaseResponse<?> deleteBook(PostDeleteBookRequest request) {
        Optional<Book> optionalSach = bookRepository.findById(request.getBookId());

        if (!optionalSach.isPresent()) {
            return null;
        }
        Book book = optionalSach.get();
        bookRepository.delete(book);

        return new  BaseResponse<>();
    }

}
