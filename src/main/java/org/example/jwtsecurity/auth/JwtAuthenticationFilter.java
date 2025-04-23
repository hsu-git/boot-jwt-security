package org.example.jwtsecurity.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;


    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain Chain) throws ServletException, IOException
    {
        String header = req.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7); // "Bearer "을 삭제..
            if (jwtTokenProvider.validateToken(token)) {
                Authentication auth = new UsernamePasswordAuthenticationToken();
                SecurityContextHolder.getContext().setAuthentication(auth);
                // Controller -> Authentication
            }
        }
        Chain.doFilter(req, res);   // 다음으로 넘겨줌..
    }
}
