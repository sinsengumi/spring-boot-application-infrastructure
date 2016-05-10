package com.example.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Site {
    API_INTERNAL("API（内向き）", "/api/internal", false),
    API_EXTERNAL("API（外向き）", "/api", false),
    SCREEN_ADMIN("管理画面", "/admin", true),
    SCREEN_USER("ユーザ画面", "", true);

    private String label;
    private String baseUrl;
    private boolean protectCsrf;

    // 列挙子の順番を変えると、誤判定するので注意。
    public static Site of(String url) {
        if (url == null) {
            return SCREEN_USER;
        }

        for (Site location : values()) {
            if (url.startsWith(location.baseUrl)) {
                return location;
            }
        }

        return SCREEN_USER;
    }
}


