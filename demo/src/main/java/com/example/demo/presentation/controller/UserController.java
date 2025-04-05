package com.example.demo.presentation.controller;


import com.example.demo.persistence.entity.User;
import com.example.demo.presentation.model.request.User.PostDeleteUser;
import com.example.demo.presentation.model.request.User.PostUpdateUser;
import com.example.demo.security.JwtResponse;
import com.example.demo.security.loginRequest;
import com.example.demo.presentation.service.JwtService;
import com.example.demo.presentation.service.AccountServiceImpl;
import com.example.demo.presentation.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/tai-khoan")
@Slf4j
public class UserController {
    @Autowired
    private AccountServiceImpl accountServiceImpl;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtSevice;

    @PostMapping("/dang-ky")
    public ResponseEntity<?> dangKyNguoiDung(@Validated @RequestBody User nguoiDung){
        ResponseEntity<?> response= accountServiceImpl.dangKyNguoiDung(nguoiDung);
        return response;
    }
    @PostMapping("/dang-nhap")
    public ResponseEntity<?> dangNhap(@RequestBody loginRequest loginRequest){
        try {
            Authentication authentication=authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword())
            );
            if(authentication.isAuthenticated()){
                final String jwt=jwtSevice.generateToken(loginRequest.getUsername());
                return ResponseEntity.ok(new JwtResponse(jwt));
            }
        }catch (AuthenticationException a){
            return ResponseEntity.badRequest().body("tên đăng nhập hoặc mật khẩu không chính xác");
        }
        return ResponseEntity.badRequest().body("xác thực không thành công");
    }
    @PostMapping("/capnhat")
    public ResponseEntity<?> capNhatNguoiDung(@RequestBody PostUpdateUser request){
        log.info("info {}",request);
        accountServiceImpl.capNhatNguoiDung(request);
        return ResponseEntity.ok("cập nhật người dùng thành công");
    }
    @PostMapping("/xoa")
    public ResponseEntity<?> xoaNguoiDung(@RequestBody PostDeleteUser request) {
        accountServiceImpl.xoaNguoiDung(request);
        return ResponseEntity.ok("Xóa người dùng thành công");
    }
}
