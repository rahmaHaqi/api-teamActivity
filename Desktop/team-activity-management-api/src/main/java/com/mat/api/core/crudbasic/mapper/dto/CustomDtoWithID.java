package com.mat.api.core.crudbasic.mapper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class CustomDtoWithID implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

    private String createdBy;

    private String lastModifiedBy;

    public Optional<String> getCreatedBy() {
        if (createdBy != null)
            return Optional.of(createdBy);
        return Optional.empty();
    }

    public Optional<String> getLastModifiedBy() {
        if (lastModifiedBy != null)
            return Optional.of(lastModifiedBy);
        return Optional.empty();
    }

    public Optional<LocalDateTime> getCreatedDate() {
        if (createdDate != null)
            return Optional.of(createdDate);
        return Optional.empty();
    }

    public Optional<LocalDateTime> getLastModifiedDate() {
        if (lastModifiedDate != null)
            return Optional.of(lastModifiedDate);
        return Optional.empty();
    }


}
