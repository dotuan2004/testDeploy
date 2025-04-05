package com.example.demo.presentation.service.serviceImpl;

import com.example.demo.persistence.Repository.DiscountCodeRepository;
import com.example.demo.persistence.entity.DiscountCode;
import com.example.demo.presentation.model.request.DiscountCode.PostCreateDiscountCode;
import com.example.demo.presentation.service.DiscountCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DiscountCodeServiceImpl implements DiscountCodeService {
    @Autowired
    private DiscountCodeRepository discountCodeRepository;
    @Override
    public void postCreateDiscountCode(PostCreateDiscountCode postCreateDiscountCode) {
        Optional<DiscountCode> existingDiscountCode = discountCodeRepository.findByCode(postCreateDiscountCode.getCode());
        if (existingDiscountCode.isPresent()) {
            throw new IllegalArgumentException("Mã giảm giá đã tồn tại.");
        }
        if (postCreateDiscountCode.getDiscount() == null || postCreateDiscountCode.getDiscount() <= 0) {
            throw new IllegalArgumentException("Giảm giá phải là một giá trị dương.");
        }
        if (postCreateDiscountCode.getMinOrderValue() == null || postCreateDiscountCode.getMinOrderValue() <= 0) {
            throw new IllegalArgumentException("Giá trị đơn hàng tối thiểu phải lớn hơn 0.");
        }
        if (postCreateDiscountCode.getMaxDiscountAmount() == null || postCreateDiscountCode.getMaxDiscountAmount() <= 0) {
            throw new IllegalArgumentException("Giảm giá tối đa phải lớn hơn 0.");
        }
        if (postCreateDiscountCode.getActive() == null) {
            throw new IllegalArgumentException("Trạng thái mã giảm giá không hợp lệ.");
        }
        DiscountCode discountCode = new DiscountCode();
        discountCode.setCode(postCreateDiscountCode.getCode());
        discountCode.setDiscount(postCreateDiscountCode.getDiscount());
        discountCode.setMinOrderValue(postCreateDiscountCode.getMinOrderValue());
        discountCode.setMaxDiscountAmount(postCreateDiscountCode.getMaxDiscountAmount());
        discountCode.setActive(postCreateDiscountCode.getActive());

        // Lưu mã giảm giá vào cơ sở dữ liệu
        discountCodeRepository.save(discountCode);
    }
}
