package com.mat.api.core.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;

@Component("dateTimeProvider")
public class CustomDateTimeProvider implements DateTimeProvider {

    @NotNull
    @Override
    public Optional<TemporalAccessor> getNow() {
        return Optional.of(LocalDateTime.now());
    }
}
