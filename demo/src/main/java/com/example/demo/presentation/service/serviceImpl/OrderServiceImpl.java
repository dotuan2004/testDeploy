package com.example.demo.presentation.service.serviceImpl;

import com.example.demo.persistence.Repository.BookRepository;
import com.example.demo.persistence.Repository.OrderRepository;
import com.example.demo.persistence.Repository.UserRepository;
import com.example.demo.persistence.entity.Book;
import com.example.demo.persistence.entity.Order;
import com.example.demo.persistence.entity.OrderDetail;
import com.example.demo.persistence.entity.User;
import com.example.demo.presentation.model.request.Order.PostCreateOrderRequest;
import com.example.demo.presentation.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    public void saveOrder(PostCreateOrderRequest request) {
        log.info("log ra thong tin {}", request);
        if (request == null) {
            throw new IllegalArgumentException("Request must not be null");
        }

        Order order = new Order();
        order.setCreatedDate(new Date());
        order.setBillingAddress(request.getBillingAddress());
        order.setShippingAddress(request.getShippingAddress());
        order.setTotalAmount(request.getTotalAmount());
        order.setPhoneNumberOrder(request.getPhoneNumberOrder());
        order.setPaymentStatus(request.getPaymentStatus());
        order.setDeliveryStatus(request.getDeliveryStatus());

        if (request.getOrderDetails() == null || request.getOrderDetails().isEmpty()) {
            throw new IllegalArgumentException("OrderDetails must not be null or empty");
        }

        if (request.getUserId() != null) {
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + request.getUserId()));
            order.setUser(user);
        } else {
            throw new IllegalArgumentException("User ID must not be null");
        }

        List<OrderDetail> orderDetails = request.getOrderDetails().stream().map(detailDTO -> {
            if (detailDTO == null) {
                throw new IllegalArgumentException("OrderDetail must not be null");
            }

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setQuantity(detailDTO.getQuantity());
            orderDetail.setPrice(detailDTO.getPrice());

            if (detailDTO.getBookId() == null) {
                throw new IllegalArgumentException("Book ID must not be null in OrderDetail");
            }
            System.out.println("Book ID: " + detailDTO.getBookId());
            Book book = bookRepository.findById(detailDTO.getBookId())
                    .orElseThrow(() -> new RuntimeException("Book not found with ID: " + detailDTO.getBookId()));
            orderDetail.setBook(book);

            orderDetail.setOrder(order);
            return orderDetail;
        }).toList();
        order.setOrderDetails(orderDetails);

        // Lưu Order vào cơ sở dữ liệu
        orderRepository.save(order);
    }
}
