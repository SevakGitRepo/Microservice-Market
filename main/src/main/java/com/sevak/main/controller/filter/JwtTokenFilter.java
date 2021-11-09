package com.sevak.main.controller.filter;

import com.sevak.main.excep.JwtAuthenticationException;
import com.sevak.main.valid.TokenValidity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//nayuma te token ka te che

@Component
public class JwtTokenFilter extends GenericFilterBean {
    @Autowired
    private TokenValidity tokenValidity;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = tokenValidity.resolveToken((HttpServletRequest) servletRequest);
            if (token == null ) {
                throw  new JwtAuthenticationException("Jwt token empty");
                }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
