package com.mat.api.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class CorsConfig implements WebMvcConfigurer {


    @Value("${security.cors.allowedHeaders}")
    private String[] allowedHeaders;

    @Value("${security.cors.allowedOrigins}")
    private String[] allowedOrigins;

    @Value("${security.cors.allowedMethods}")
    private String[] allowedMethods;

    @Value("${security.cors.allowCredentials}")
    private boolean allowCredentials;

    @Value("${security.cors.maxAge}")
    private Long maxAge;

    @Value("${security.cors.configurationPath}")
    private String corsConfigurationPath;


    @Bean(name = "corsConfigurationSource")
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration config = new CorsConfiguration();

        if (allowedOrigins != null && allowedOrigins.length > 0) {
            for (final String origin : allowedOrigins) {
                config.addAllowedOrigin(origin);
            }
        }

        if (allowedHeaders != null && allowedHeaders.length > 0) {
            for (final String header : allowedHeaders) {
                config.addAllowedHeader(header);
            }
        }

        config.setAllowedMethods(Arrays.asList(allowedMethods));

        config.setAllowedOrigins(Arrays.asList(allowedOrigins));

        config.setAllowCredentials(true);

        config.setMaxAge(maxAge);

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", config);

        return source;
    }
}
