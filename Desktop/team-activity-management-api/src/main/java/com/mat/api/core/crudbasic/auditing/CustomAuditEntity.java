package com.mat.api.core.crudbasic.auditing;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.Optional;

@MappedSuperclass
@Setter
@EntityListeners(AuditingEntityListener.class)
public abstract class CustomAuditEntity implements Auditable<String, Long, LocalDateTime> {

    @JsonIgnore
    @Column(name = "created_by")
    private String createdBy;

    @JsonIgnore
    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @JsonIgnore
    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @JsonIgnore
    @Column(name = "last_modification_date")
    @UpdateTimestamp
    private LocalDateTime lastModifiedDate;

    @Override
    @Transient
    public boolean isNew() {
        return null == getId();
    }


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
