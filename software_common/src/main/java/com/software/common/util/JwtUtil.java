package com.software.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Date;

/**
 *
 * 认证工具类
 */
@ConfigurationProperties("jwt.config")
public class JwtUtil {

    public String key;//秘钥

    public long ttl;//过期时间

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }

    /**
     * 生成jwt
     */
    public String createJWT(String id,String subject,String role){

        long nowTime = System.currentTimeMillis();
        Date now = new Date(nowTime);
        JwtBuilder builder = Jwts.builder().setId(id)
                .setSubject(subject)
                .claim("role",role)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256,key);

        //存在过期时间
        if(ttl>0){
            builder.setExpiration(new Date(nowTime+ttl));
        }

        return builder.compact();
    }

    /**
     * 解析jwt
     */

    public Claims parseJWT(String jwtStr){

        return Jwts.parser().setSigningKey(key).parseClaimsJws(jwtStr).getBody();

    }


}
