package com.example.demo.security;

public class EndPoints {

    public static final String front_end_host="http://localhost:3000";
    public static final String[] PUBLIC_GET_ENDPOINS = {
            "/sach",
            "/sach/**",
            "/hinh-anh",
            "/hinh-anh/**",
            "/nguoi-dung/search/existsByTenDangNhap",
            "/nguoi-dung/search/existsByEmail",
    };

    public static final String[] PUBLIC_POST_ENDPOINS = {
            "/tai-khoan/dang-ky",
            "/tai-khoan/dang-nhap",
    };
    public static final String[] ADMIN_GET_ENDPOINS = {
            "/nguoi-dung",
            "/nguoi-dung/**",
    };
    public static final String[] ADMIN_POST_ENDPOINS = {
            "/sach",
    };
}
