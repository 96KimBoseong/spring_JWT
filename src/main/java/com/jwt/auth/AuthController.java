package com.jwt.auth;

import com.jwt.entity.UserRoleEnum;
import com.jwt.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class AuthController {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil){
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/create-jwt")
    public String createJwt(HttpServletResponse response){

        String token = jwtUtil.createToken("KBS", UserRoleEnum.USER);// jwt 생성

        jwtUtil.addJwtToCookie(token,response); // 생성된 jwt 쿠키에 담기
        return "createJwt success : " + token;
    }

    @GetMapping("/get-jwt")
    public String getJwt(@CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue){

        String token = jwtUtil.substringToken(tokenValue); // jwt 접두사 제거 순수 토큰값 추출

        if (!jwtUtil.validateToken(token)){
            throw new IllegalArgumentException("token error");
        } // 토큰 검증

        Claims info = jwtUtil.getUserInfoFromToken(token);
        // 사용자 정보 가져오기

        String username = info.getSubject();
        System.out.println("username: " + username);
        //사용자 username

        String authority = (String) info.get(JwtUtil.AUTHORIZATION_KEY);
        System.out.println("authority: " + authority);
        //사용자 권한

        return "getJwt success : " + username + " " + authority;

    }




}
