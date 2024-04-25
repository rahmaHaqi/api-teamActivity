package com.mat.api.core.config;

import com.mat.api.core.crudbasic.auditing.AuditorAwareImpl;
import com.mat.api.core.errorhandling.businessexeption.BusinessErrorCode;
import com.mat.api.core.errorhandling.exeption.CommonStatusCode;
import com.mat.api.core.errorhandling.exeption.ErrorCode;
import io.github.logger.controller.aspect.GenericControllerAspect;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware", dateTimeProviderRef = "dateTimeProvider")
public class ConfigUtils {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public GenericControllerAspect genericControllerAspect() {
        final GenericControllerAspect aspect = new GenericControllerAspect();
        aspect.setEnableDataScrubbing(true);
        aspect.setDefaultScrubbedValue("*******");
        aspect.setParamBlacklistRegex("account.*");
        aspect.setCustomParamBlacklist(new HashSet<>(List.of("securityProtocol")));
        return aspect;
    }


    @Bean
    public Map<String, BusinessErrorCode> exceptionToErrorCodeMap() {
        final Map<String, BusinessErrorCode> exceptionToErrorCodeMap = new HashMap<>();
        exceptionToErrorCodeMap.put("NullPointerException", ErrorCode.INVALID_FORMAT);
        exceptionToErrorCodeMap.put("JsonParseException", ErrorCode.INVALID_FORMAT);
        exceptionToErrorCodeMap.put("NoSuchFileException", CommonStatusCode.CONFIG_NOT_FOUND);
        return exceptionToErrorCodeMap;
    }

    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAwareImpl();
    }

}

