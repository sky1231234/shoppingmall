package com.project.shop.global.config.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.project.shop.global.config.security.TokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    private final TokenProvider tokenProvider;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String jwt = resolveToken( (HttpServletRequest) servletRequest);
        String requestURI = httpServletRequest.getRequestURI();

        if(jwt != null && tokenProvider.validateToken(jwt)){
            Authentication authentication = tokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            logger.info("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}", authentication.getName(), requestURI);
        }else{
            logger.info("유효한 JWT 토큰이 없습니다., uri: {}", requestURI);
        }

        filterChain.doFilter(httpServletRequest, response);
    }

    private String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");

        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")){
            return bearerToken.substring(7);
        }
        return null;
    }
}
