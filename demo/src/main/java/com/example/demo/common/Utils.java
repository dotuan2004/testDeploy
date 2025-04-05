package com.example.demo.common;

import java.text.Normalizer;

public class Utils {
    public static String removeVietnameseAccentsAndSpaces(String input) {
        // Bỏ dấu tiếng Việt
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        String withoutAccents = normalized.replaceAll("\\p{M}", "");

        // Loại bỏ khoảng trắng
        return withoutAccents.replaceAll("\\s+", "");
    }
}
