package com.mat.api.core.crudbasic.mapper.convertter;


import com.mat.api.core.crudbasic.auditing.CustomAuditEntity;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class OneWayAbstractListConverter<ENTITY extends CustomAuditEntity, DTO> implements OneWayListConverter<ENTITY, DTO> {

    /**
     * Convert from a model model (entity) list to the businessexception model (dto) list
     *
     * @param source list of entities
     * @return target list of dtos
     */
    public List<DTO> convertTo(Collection<ENTITY> source) {
        if (CollectionUtils.isEmpty(source)) {
            return new ArrayList<DTO>();
        }
        List<DTO> target = new ArrayList<DTO>();

        source.forEach(clazz -> target.add(this.convertTo(clazz)));
        return target;

    }

    public List<DTO> subEntityTo(Collection<? extends ENTITY> source) {
        if (CollectionUtils.isEmpty(source)) {
            return new ArrayList<DTO>();
        }
        List<DTO> target = new ArrayList<DTO>();

        source.forEach(clazz -> target.add(this.convertTo(clazz)));
        return target;

    }

    public List<Long> convertToIds(Collection<ENTITY> source) {
        if (CollectionUtils.isEmpty(source)) {
            return new ArrayList<>();
        }

        List<Long> target = new ArrayList<>();

        for (CustomAuditEntity entity : source) {
            target.add(entity.getId());
        }

        return target;
    }
}