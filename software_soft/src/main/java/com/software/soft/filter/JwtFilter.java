package com.software.soft.filter;

import com.software.common.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@Component
public class JwtFilter extends HandlerInterceptorAdapter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //规定请求头带有authorization  token存放在请求头中
        //不允许改变
        final String requestHeader = request.getHeader("token");
        System.out.println("requestHeader:"+requestHeader);
        //规定 以bearer开头
        if(requestHeader!=null && requestHeader.startsWith("Bearer ")){
            //获取token
            final String token = requestHeader.substring(7);

            //解析token
            Claims claims = jwtUtil.parseJWT(token);

            if(claims!=null){

                //管理员登录
                if("admin".equals(claims.get("role"))){
                    request.setAttribute("admin_claims",claims);
                }

                //普通用户登录
                if("user".equals(claims.get("role"))){
                    request.setAttribute("user_claims",claims);
                }

            }

        }

        return true;
    }
}
