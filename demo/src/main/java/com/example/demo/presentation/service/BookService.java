package com.example.demo.presentation.service;

import com.example.demo.presentation.model.request.Book.PostCreateBookRequest;
import com.example.demo.presentation.model.request.Book.PostDeleteBookRequest;
import com.example.demo.presentation.model.request.Book.PostUpdateBookRequest;
import com.example.demo.presentation.model.response.BaseResponse;

public interface BookService {
    BaseResponse<?> addBook(PostCreateBookRequest request);
    BaseResponse<?> updateBook(PostUpdateBookRequest request);
    BaseResponse<?> deleteBook(PostDeleteBookRequest request);
}
