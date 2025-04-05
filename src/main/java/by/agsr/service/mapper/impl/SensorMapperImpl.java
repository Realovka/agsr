package by.agsr.service.mapper.impl;

import by.agsr.controller.dto.SensorDto;
import by.agsr.dao.entity.Sensor;
import by.agsr.dao.entity.TypeEntity;
import by.agsr.dao.entity.UnitEntity;
import by.agsr.service.mapper.RangeMapper;
import by.agsr.service.mapper.SensorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SensorMapperImpl implements SensorMapper {

    private final RangeMapper rangeMapper;

    public Sensor mapToEntity(SensorDto sensorDto) {
        return Sensor.builder()
                .name(sensorDto.getName())
                .model(sensorDto.getModel())
                .range(rangeMapper.mapToEntity(sensorDto.getRangeDto()))
                .typeEntity(new TypeEntity(null, sensorDto.getType()))
                .unitEntity(new UnitEntity(null, sensorDto.getUnit()))
                .location(sensorDto.getLocation())
                .description(sensorDto.getDescription())
                .build();
    }

    public SensorDto mapToDto(Sensor sensor) {
        return SensorDto.builder()
                .name(sensor.getName())
                .model(sensor.getModel())
                .rangeDto(rangeMapper.mapToDto(sensor.getRange()))
                .type(sensor.getTypeEntity().getType())
                .unit(sensor.getUnitEntity() != null ? sensor.getUnitEntity().getUnit() : null)
                .location(sensor.getLocation())
                .description(sensor.getDescription())
                .build();
    }

    public List<SensorDto> mapToListDto(List<Sensor> sensors) {
        return sensors
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
}
