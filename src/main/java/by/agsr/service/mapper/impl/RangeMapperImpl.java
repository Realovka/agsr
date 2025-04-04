package by.agsr.service.mapper.impl;

import by.agsr.controller.dto.RangeDto;
import by.agsr.dao.entity.Range;
import by.agsr.service.mapper.RangeMapper;
import org.springframework.stereotype.Component;

@Component
public class RangeMapperImpl implements RangeMapper {

    public Range mapToEntity(RangeDto rangeDto) {
        return Range.builder()
                .rangeFrom(rangeDto.getFrom())
                .rangeTo(rangeDto.getTo())
                .build();
    }

    public RangeDto mapToDto(Range range) {
        return RangeDto.builder()
                .from(range.getRangeFrom())
                .to(range.getRangeTo())
                .build();
    }
}
