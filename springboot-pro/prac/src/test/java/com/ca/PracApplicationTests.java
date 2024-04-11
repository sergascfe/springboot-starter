package com.ca;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;

//@SpringBootTest
class PracApplicationTests {

    @Test
    void contextLoads() {
    }

    //测试jwt令牌的生成
    @Test
    public void testGenJwt(){
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("name","tom");
        String jwt= Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,"itheima")//签名算法
                .setClaims(claims)//自定义内容
                .setExpiration(new Date(System.currentTimeMillis()+3600*1000))//设置有效期为1h
                .compact();
        System.out.println(jwt);
    }




    @Test
    public void testParseJwt(){
        Claims claims= Jwts.parser()
                .setSigningKey("itheima")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidG9tIiwiaWQiOjEsImV4cCI6MTcxMjU1MDMzNX0.fGoagNVpJNZOuekzcVdvuMBshXj8ivB3pQ_i7k3xUAc")
                .getBody();
        System.out.println(claims);
    }
}
