package com.mat.api.core.crudbasic.mapper.convertter;

import com.mat.api.core.crudbasic.auditing.CustomAuditEntity;

import java.util.Collection;
import java.util.List;

public interface ListConverter<ENTITY extends CustomAuditEntity, DTO> extends OneWayListConverter<ENTITY, DTO>, Converter<ENTITY, DTO> {
    /**
     * Convert from a businessexception model (dto) list to the model model (entity) list
     *
     * @param source list of dtos
     * @return target list of entities
     */
    public List<ENTITY> convertFrom(Collection<DTO> source);
}
