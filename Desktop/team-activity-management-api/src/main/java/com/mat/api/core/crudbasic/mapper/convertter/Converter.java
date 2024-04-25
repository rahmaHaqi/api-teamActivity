package com.mat.api.core.crudbasic.mapper.convertter;


import com.mat.api.core.crudbasic.auditing.CustomAuditEntity;


public interface Converter<E extends CustomAuditEntity, D> extends OneWayConverter<E, D> {

    /**
     * Convert from a businessexception model (dto) to the model model (entity)
     *
     * @param source dto
     * @return target entity
     */
    public E convertFrom(D source);
}
