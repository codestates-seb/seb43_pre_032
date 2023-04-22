package com.dudung.preproject.auth.interceptor;

import com.dudung.preproject.auth.utils.ErrorResponder;
import com.dudung.preproject.auth.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
@Component
public class JwtParseInterceptor implements HandlerInterceptor {
    private final JwtUtils jwtUtils;
    private static final ThreadLocal<Long> authenicatedMemberId = new ThreadLocal<>();

    public JwtParseInterceptor(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    public static long getAuthenticatedMemberId() {
        return authenicatedMemberId.get();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            Map<String, Object> claims = jwtUtils.getJwsClaimsFromRequest(request);
            Object memberId = claims.get("memberId");
            if (memberId != null) {
                authenicatedMemberId.set(Long.valueOf(memberId.toString()));
                return true;
            } else {
                // memberId가 null인 경우에 대한 예외 처리
                ErrorResponder.sendErrorResponse(response, HttpStatus.UNAUTHORIZED);
                return false;
            }
        } catch (Exception e) {
            ErrorResponder.sendErrorResponse(response, HttpStatus.UNAUTHORIZED);
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        authenicatedMemberId.remove();
    }
}
