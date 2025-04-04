package by.agsr.service.mapper;

import by.agsr.controller.dto.SensorDto;
import by.agsr.dao.entity.Sensor;

import java.util.List;

public interface SensorMapper {
    Sensor mapToEntity(SensorDto sensorDto);
    SensorDto mapToDto(Sensor sensor);
    List<SensorDto> mapToListDto(List<Sensor> sensors);
}
