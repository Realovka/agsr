package by.agsr.service.impl;

import by.agsr.controller.dto.SensorDto;
import by.agsr.dao.entity.Sensor;
import by.agsr.dao.repository.SensorRepository;
import by.agsr.exception.EntityNotFoundException;
import by.agsr.service.SensorService;
import by.agsr.service.mapper.SensorMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class SensorServiceImpl implements SensorService {

    private final SensorRepository sensorRepository;
    private final SensorMapper sensorMapper;

    @Override
    @Transactional
    public Long createSensor(SensorDto sensorDto) {
        Sensor sensor = sensorMapper.mapToEntity(sensorDto);
        return sensorRepository.save(sensor).getId();
    }

    @Override
    public SensorDto getById(Long id) {
        Sensor sensor = sensorRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException(String.format("No such entity with id %s", id)));
        return sensorMapper.mapToDto(sensor);
    }

    @Override
    public List<SensorDto> getAll() {
        List<Sensor> sensors = sensorRepository.findAll();
        return sensorMapper.mapToListDto(sensors);
    }

    @Override
    @Transactional
    public void deleteSensor(Long id) {
        sensorRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException(String.format("No such entity with id %s", id)));
        sensorRepository.deleteById(id);
    }

    @Override
    @Transactional
    public SensorDto updateSensor(Long id, SensorDto sensorDto) {
        Sensor sensor = sensorRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException(String.format("No such entity with id %s", id)));
        buildSensorForUpdate(sensorDto, sensor);
        sensor = sensorRepository.save(sensor);
        return sensorMapper.mapToDto(sensor);
    }

    private void buildSensorForUpdate(SensorDto sensorDto, Sensor sensor) {
        if (StringUtils.isNotEmpty(sensorDto.getName())) {
            sensor.setName(sensorDto.getName());
        }
        if (StringUtils.isNotEmpty(sensorDto.getModel())) {
            sensor.setModel(sensorDto.getModel());
        }
        if (sensorDto.getRangeDto().getFrom() != 0) {
            sensor.getRange().setRangeFrom(sensorDto.getRangeDto().getFrom());
        }
        if (sensorDto.getRangeDto().getTo() != 0) {
            sensor.getRange().setRangeTo(sensorDto.getRangeDto().getTo());
        }
        if (sensorDto.getType() != null) {
            sensor.setType(sensorDto.getType());
        }
        if (sensorDto.getUnit() != null) {
            sensor.setUnit(sensorDto.getUnit());
        }
        if (StringUtils.isNotEmpty(sensorDto.getLocation())) {
            sensor.setLocation(sensorDto.getLocation());
        }
        if (StringUtils.isNotEmpty(sensorDto.getDescription())) {
            sensor.setDescription(sensorDto.getDescription());
        }
    }
}
