package com.mat.api.core.crudbasic.auditing;

import com.mat.api.core.security.services.UserDetailsImpl;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    protected static final Logger LOGGER = LoggerFactory.getLogger(AuditorAwareImpl.class);

    @NotNull
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null &&!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();

            try {
                UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();

                if (user != null) {
                    return Optional.of(user.getUsername());
                }
            } catch (Exception e) {
                if(!StringUtils.isEmpty(currentUserName)){
                    return Optional.of(currentUserName);
                }

                LOGGER.error(e.getMessage(), e);
            }
        }

        return Optional.of("System");
    }
}
