package com.goan.football.config;

import com.goan.football.repositories.TokenRepository;
import com.goan.football.utils.CachingHttpServletRequestWrapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        HttpServletRequest wrappedRequest = new CachingHttpServletRequestWrapper(request);
        if (shouldExcludeAuthenticationFilter(wrappedRequest, "authenticate")) {
            filterChain.doFilter(wrappedRequest, response);
            return;
        }
        if (shouldExcludeAuthenticationFilter(wrappedRequest, "logout")) {
            filterChain.doFilter(wrappedRequest, response);
            return;
        }
        if (shouldExcludeAuthenticationFilter(wrappedRequest, "tokenIsValid")) {
            filterChain.doFilter(wrappedRequest, response);
            return;
        }

        final String authHeader = wrappedRequest.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        log.info("authHeader : " + authHeader);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(wrappedRequest, response);
            return;
        }
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            var isTokenValid = tokenRepository.findByToken(jwt)
                    .map(t -> !t.isExpired() && !t.isRevoked())
                    .orElse(false);
            if (jwtService.isTokenValid(jwt, userDetails) && isTokenValid) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(wrappedRequest));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(wrappedRequest, response);

    }

    private boolean shouldExcludeAuthenticationFilter(HttpServletRequest request, String methodName) throws IOException {
        if (request instanceof HttpServletRequestWrapper) {
            HttpServletRequestWrapper wrapper = (HttpServletRequestWrapper) request;
            String requestPayload = wrapper.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            String authenticateFieldName = methodName;
            return requestPayload.contains(authenticateFieldName);
        }
        return false;
    }
}