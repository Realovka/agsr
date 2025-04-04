package by.agsr.service;

import by.agsr.controller.dto.SensorDto;

import java.util.List;

public interface SensorService {
    Long createSensor(SensorDto sensorDto);
    SensorDto getById(Long id);
    List<SensorDto> getAll();
    void deleteSensor(Long id);
    SensorDto updateSensor(Long id, SensorDto sensorDto);
}
