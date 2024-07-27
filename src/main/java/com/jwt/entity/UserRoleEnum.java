package com.jwt.entity;

public enum UserRoleEnum {
    USER(Authority.USER),  // 사용자 권한
    ADMIN(Authority.ADMIN);  // 관리자 권한
    // 열거목록정의할때 내부 클래스의 어쏘리티도 같이
    private final String authority;

    UserRoleEnum(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    public static class Authority {
        public static final String USER = "ROLE_USER";
        public static final String ADMIN = "ROLE_ADMIN";
    }
}
