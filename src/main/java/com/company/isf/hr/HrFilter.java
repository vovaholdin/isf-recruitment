package com.company.isf.hr;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class HrFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                     HttpServletResponse response,
                                     FilterChain filterChain)
            throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.startsWith("/hr")){
            Boolean loggedIn = (Boolean) request.getSession().getAttribute("HR_LOGGED_IN");
            if (loggedIn == null || !loggedIn){
                response.sendRedirect("/loghr");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
