package com.mat.api.core.crudbasic.mapper.convertter;

import com.mat.api.core.crudbasic.auditing.CustomAuditEntity;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public abstract class BasicConverter<ENTITY extends CustomAuditEntity, DTO> extends OneWayAbstractListConverter<ENTITY, DTO> implements ListConverter<ENTITY, DTO> {
    /**
     * Convert from a list of businessexception model (dto) to a list of model model (entity)
     *
     * @param source list of dtos
     * @return target list of entities
     */
    public List<ENTITY> convertFrom(Collection<DTO> source) {
        if (CollectionUtils.isEmpty(source)) {
            return null;
        }
        List<ENTITY> target = new ArrayList<ENTITY>();

        source.forEach(clazz -> target.add(this.convertFrom(clazz)));

        return target;
    }
}
