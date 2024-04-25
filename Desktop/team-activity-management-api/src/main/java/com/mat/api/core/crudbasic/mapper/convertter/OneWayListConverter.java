package com.mat.api.core.crudbasic.mapper.convertter;

import com.mat.api.core.crudbasic.auditing.CustomAuditEntity;

import java.util.Collection;
import java.util.List;

public interface OneWayListConverter<ENTITY extends CustomAuditEntity, DTO> extends OneWayConverter<ENTITY, DTO> {

    /**
     * Convert from a model model (entity) list to the businessexception model (dto) list
     *
     * @param source list of entities
     * @return target list of dtos
     */
    public List<DTO> convertTo(Collection<ENTITY> source);

    public List<Long> convertToIds(Collection<ENTITY> source);
}
