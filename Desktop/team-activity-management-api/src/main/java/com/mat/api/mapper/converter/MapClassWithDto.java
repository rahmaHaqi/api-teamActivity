package com.mat.api.mapper.converter;

import lombok.RequiredArgsConstructor;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MapClassWithDto implements IMapClassWithDto {

    private final ModelMapper modelMapper;

    @Override
    public <I, O> O  convert(final I input, Class<O> outputClass) {
        if (input == null) {
            return null;
        }
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setPropertyCondition(Conditions.isNotNull());


        return modelMapper.map(input, outputClass);
    }

}
