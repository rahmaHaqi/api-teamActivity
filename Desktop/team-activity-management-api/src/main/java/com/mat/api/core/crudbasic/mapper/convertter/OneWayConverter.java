package com.mat.api.core.crudbasic.mapper.convertter;

import com.mat.api.core.crudbasic.auditing.CustomAuditEntity;

public interface OneWayConverter<ENTITY extends CustomAuditEntity, DTO> {

    /**
     * Convert from a model model (entity) to the businessexception model (dto)
     *
     * @param source entity
     * @return target dto
     */
    public DTO convertTo(ENTITY source);
}
