package by.agsr.service.mapper.impl;

import by.agsr.controller.dto.SensorDto;
import by.agsr.dao.entity.Sensor;
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
                .type(sensorDto.getType())
                .unit(sensorDto.getUnit())
                .location(sensorDto.getLocation())
                .description(sensorDto.getDescription())
                .build();
    }

    public SensorDto mapToDto(Sensor sensor) {
        return SensorDto.builder()
                .name(sensor.getName())
                .model(sensor.getModel())
                .rangeDto(rangeMapper.mapToDto(sensor.getRange()))
                .type(sensor.getType())
                .unit(sensor.getUnit())
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
