package com.example.demo.presentation.service;

import com.example.demo.persistence.entity.Role;
import com.example.demo.persistence.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Component
public class JwtService {
    @Autowired
    private UserService userService;
    private static final String SERECT = "4832984729375298456923750170592385623985692560237501325925874572305";


    public String generateToken(String tenDangNhap) {
        boolean isAdmin=false;
        boolean isStaff=false;
        boolean isUser=false;
        Map<String,Object> claims =new HashMap<>();
        Optional<User> nguoiDung=userService.findByUserName(tenDangNhap);

        if (nguoiDung.isPresent() && nguoiDung.get().getRoleList().size()>0){
            List<Role> list = nguoiDung.get().getRoleList();
            for (Role q: list) {
                if(q.getRoleName().equals("ADMIN")){
                    isAdmin=true;
                }
                if(q.getRoleName().equals("STAFF")) {
                    isStaff=true;
                }
                if(q.getRoleName().equals("USER")){
                    isUser=true;
                }
            }
        }
        claims.put("isAdmin", isAdmin);
        claims.put("isStaff", isStaff);
        claims.put("isUser", isUser);
        claims.put("userId", nguoiDung.get().getUserId());
        return createToken(claims, tenDangNhap);
    }

    private String createToken(Map<String, Object> claims, String tenDangNhap) {
        return Jwts.builder()
                .setClaims(claims) // Đặt các claim vào token
                .setSubject(tenDangNhap) // Đặt tên đăng nhập làm subject của token
                .setIssuedAt(new Date(System.currentTimeMillis())) // Đặt thời gian phát hành
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // Đặt thời gian hết hạn (1 giờ)
                .signWith(SignatureAlgorithm.HS256,getSigneKey()) // Mã hóa token bằng thuật toán HS256 và chìa khóa bí mật
                .compact();
    }
    public Key getSigneKey(){
        byte[] keyBytes= Decoders.BASE64.decode(SERECT);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Phương thức trích xuất tất cả các claims từ token
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSigneKey()) // Lấy chìa khóa bí mật từ phương thức getSigningKey
                .parseClaimsJws(token) // Phân tích JWT
                .getBody(); // Trả về phần body của JWT chứa các claims
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimsTFunction){
        final Claims claims=extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    public Date extractExpiration (String token) {
        return extractClaim (token, Claims::getExpiration);
    }
    // Kiểm tra tời gian hết hạn từ JWT

    public String extractUsername (String token){
        return extractClaim (token, Claims::getSubject);
    }
    private Boolean isTokenExpired (String token) {
        return extractExpiration(token).before(new Date());
    }
    // Kiểm tra tính hợp lệ

    public Boolean validateToken(String token, UserDetails userDetails){
        final String tenDangNhap = extractUsername(token);
        return (tenDangNhap.equals(userDetails.getUsername()) &&!isTokenExpired(token));
    }
}
