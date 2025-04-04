package by.agsr.service.mapper;

import by.agsr.controller.dto.RangeDto;
import by.agsr.dao.entity.Range;

public interface RangeMapper {
    Range mapToEntity(RangeDto rangeDto);
    RangeDto mapToDto(Range range);
}
