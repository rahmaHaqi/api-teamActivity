package com.mat.api.core.security.jwt;

import com.mat.api.core.security.services.UserDetailsServiceImpl;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AuthTokenFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthTokenFilter.class);
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    private String getStringValue(final byte[] contentAsByteArray, final String characterEncoding) {
        try {
            return new String(contentAsByteArray, characterEncoding);
        } catch (final UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    protected void doFilterInternal(@NotNull final HttpServletRequest request, @NotNull final HttpServletResponse response, @NotNull final FilterChain filterChain)
            throws ServletException, IOException {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final long requestTime = System.currentTimeMillis();
        String formattedDate = sdf.format(new Date(System.currentTimeMillis()));
        final ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        final String requestBody = getStringValue(requestWrapper.getContentAsByteArray(),
                request.getCharacterEncoding());

        LOGGER.info("BEFORE PROCESSING : DATE={}; METHOD={}; REQUESTURI={}; REQUEST PAYLOAD={};",
                formattedDate, request.getMethod(), request.getRequestURI(), requestBody);

        String username = "";
        try {
            final String jwt = parseJwt(request);
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                username = jwtUtils.getUserNameFromJwtToken(jwt);
                final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                final UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (final Exception e) {
            LOGGER.error("Cannot set user authentication: %s", e);
        }

        final ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        filterChain.doFilter(requestWrapper, responseWrapper);
        final long requestTimeTaken = System.currentTimeMillis() - requestTime;
        formattedDate = sdf.format(new Date(System.currentTimeMillis()));
        final String responseBody = getStringValue(responseWrapper.getContentAsByteArray(),
                response.getCharacterEncoding());

        LOGGER.info("AFTER PROCESSING : DATE={};  REQUESTURI={};  USER={}; RESPONSE CODE={}; RESPONSE={}; TIME TAKEN={}",
                formattedDate, request.getRequestURI(), username, response.getStatus(), responseBody, requestTimeTaken);
        responseWrapper.copyBodyToResponse();
    }


    private String parseJwt(final HttpServletRequest request) {
        final String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }

        return null;
    }
}
