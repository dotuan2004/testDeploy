package com.example.demo.presentation.controller;

import com.example.demo.presentation.model.request.Book.PostCreateBookRequest;
import com.example.demo.presentation.model.request.Book.PostDeleteBookRequest;
import com.example.demo.presentation.model.request.Book.PostUpdateBookRequest;
import com.example.demo.presentation.model.response.BaseResponse;
import com.example.demo.presentation.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("book")
@Slf4j
public class BookController {
    @Autowired
    private BookService bookService;
    @GetMapping("/add")
    public BaseResponse<?> addBook(@RequestBody PostCreateBookRequest request){
        System.out.println("Request received: " + request);
        System.out.println("ISBN: " + request.getIsBn());
        bookService.addBook(request);
        return new BaseResponse<>();
    }
    @PostMapping("/update")
    public BaseResponse<?> updateBook(@RequestBody PostUpdateBookRequest request){
        bookService.updateBook(request);
        return new BaseResponse<>();
    }
    @PostMapping("/delete")
    public BaseResponse<?> deleteBook(@RequestBody PostDeleteBookRequest request){
        bookService.deleteBook(request);
        return new BaseResponse<>();
    }
}
