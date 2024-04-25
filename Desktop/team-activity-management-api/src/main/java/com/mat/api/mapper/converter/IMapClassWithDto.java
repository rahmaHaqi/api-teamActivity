package com.mat.api.mapper.converter;

public interface IMapClassWithDto {

    <I, O> O convert(final I input, Class<O> outputClass);
}
