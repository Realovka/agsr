package by.agsr.service.impl;

import by.agsr.controller.dto.SensorDto;
import by.agsr.dao.entity.Sensor;
import by.agsr.dao.entity.TypeEntity;
import by.agsr.dao.entity.UnitEntity;
import by.agsr.dao.repository.SensorRepository;
import by.agsr.dao.repository.TypeRepository;
import by.agsr.dao.repository.UnitRepository;
import by.agsr.exception.EntityNotFoundException;
import by.agsr.service.SensorService;
import by.agsr.service.mapper.SensorMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class SensorServiceImpl implements SensorService {

    private final SensorRepository sensorRepository;
    private final SensorMapper sensorMapper;
    private final TypeRepository typeRepository;
    private final UnitRepository unitRepository;

    @Override
    @Transactional
    public Long createSensor(SensorDto sensorDto) {
        Sensor sensor = sensorMapper.mapToEntity(sensorDto);
        return saveSensor(sensorDto, sensor);
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
    public List<SensorDto> getByNameAndModel(String name, String model) {
        List<Sensor> sensors = sensorRepository.findByNameAndModel(name, model);
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
        sensorRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException(String.format("No such entity with id %s", id)));
        Sensor sensor = sensorMapper.mapToEntity(sensorDto);
        sensor.setId(id);
        Long sensorId = saveSensor(sensorDto, sensor);
        sensor = sensorRepository.findById(sensorId).orElseThrow(()
                -> new EntityNotFoundException(String.format("No such entity with id %s", id)));
        return sensorMapper.mapToDto(sensor);
    }

    private Long saveSensor(SensorDto sensorDto, Sensor sensor) {
        Long typeId = getTypeEntityId(sensorDto);
        sensor.setTypeEntity(new TypeEntity(typeId));
        if (isUnitNull(sensorDto)) {
            sensor.setUnitEntity(null);
        } else {
            Long unitId = getUnitEntityId(sensorDto);
            sensor.setUnitEntity(new UnitEntity(unitId));
        }
        return sensorRepository.save(sensor).getId();
    }

    private boolean isUnitNull(SensorDto sensorDto) {
        return sensorDto.getUnit() == null;
    }

    private Long getTypeEntityId(SensorDto sensorDto) {
        Optional<TypeEntity> existingTypeEntity = typeRepository.findByType(sensorDto.getType());
        return existingTypeEntity
                .map(TypeEntity::getId)
                .orElseGet(() -> {
                    TypeEntity newTypeEntity = new TypeEntity();
                    newTypeEntity.setType(sensorDto.getType());
                    typeRepository.save(newTypeEntity);
                    return newTypeEntity.getId();
                });
    }

    private Long getUnitEntityId(SensorDto sensorDto) {
        Optional<UnitEntity> existingUnitEntity = unitRepository.findByUnit(sensorDto.getUnit());
        return existingUnitEntity
                .map(UnitEntity::getId)
                .orElseGet(() -> {
                    UnitEntity newUnitEntity = new UnitEntity();
                    newUnitEntity.setUnit(sensorDto.getUnit());
                    unitRepository.save(newUnitEntity);
                    return newUnitEntity.getId();
                });
    }
}
