package com.example.demo.presentation.service.serviceImpl;

import com.example.demo.DTO.BookDTO;
import com.example.demo.persistence.Repository.BookRepository;
import com.example.demo.persistence.entity.Book;
import com.example.demo.presentation.service.CartService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
@Slf4j
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final BookRepository bookRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Override
    public Map<String, Object> getAllCartDetails(String userId) {
        // Retrieve the entire map of user carts from Redis
        Map<String, List<BookDTO>> userCarts = (Map<String, List<BookDTO>>) redisTemplate.opsForValue().get("userCarts");

        // Initialize the map to store the result
        Map<String, Object> allCartDetails = new HashMap<>();

        // Check if the user's cart exists
        if (userCarts != null && userCarts.containsKey(userId)) {
            List<BookDTO> cartBooks = userCarts.get(userId);

            // Add the userId and the list of books to the result map
            allCartDetails.put("userId", userId);
            allCartDetails.put("cartBooks", cartBooks);
        }

        return allCartDetails;
    }



    @Override
    public void addBookToCart(String userId, Integer bookId) {
        log.info("logg{} service{}", userId, bookId);
        Optional<Book> optionalBook = bookRepository.findByBookId(bookId);

        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            BookDTO bookDTO = new BookDTO(book.getBookId(), book.getBookName(), book.getAuthor(), book.getSalePrice());

            try {
                // Retrieve the current cart for the user (Map)
                Map<String, List<BookDTO>> userCarts = (Map<String, List<BookDTO>>) redisTemplate.opsForValue().get("userCarts");
                if (userCarts == null) {
                    userCarts = new HashMap<>();
                }

                // Retrieve the cart for the specific user
                List<BookDTO> currentCart = userCarts.getOrDefault(userId, new ArrayList<>());

                // Add the book to the cart
                currentCart.add(bookDTO);

                // Update the cart in the Map and save it to Redis
                userCarts.put(userId, currentCart);
                redisTemplate.opsForValue().set("userCarts", userCarts);

            } catch (Exception e) {
                throw new RuntimeException("Lỗi khi thêm sách vào giỏ hàng", e);
            }
        } else {
            throw new RuntimeException("Sách không tồn tại");
        }
    }



}
