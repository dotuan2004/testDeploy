package com.example.demo.presentation.controller;
import com.example.demo.presentation.model.request.Order.PostCreateOrderRequest;
import com.example.demo.presentation.model.response.BaseResponse;
import com.example.demo.presentation.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/donhang")
public class OrderController {
    @Autowired
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/dathang")
    public BaseResponse<?> createOrder(@RequestBody PostCreateOrderRequest request) {
        orderService.saveOrder(request);
        return new BaseResponse<>();
    }
}
