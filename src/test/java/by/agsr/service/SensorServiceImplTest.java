package by.agsr.service;

import by.agsr.controller.dto.SensorDto;
import by.agsr.dao.entity.Sensor;
import by.agsr.dao.repository.SensorRepository;
import by.agsr.dao.repository.TypeRepository;
import by.agsr.exception.EntityNotFoundException;
import by.agsr.service.impl.SensorServiceImpl;
import by.agsr.service.mapper.SensorMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SensorServiceImplTest {

    @InjectMocks
    private SensorServiceImpl sensorService;
    @Mock
    private SensorMapper sensorMapper;
    @Mock
    private SensorRepository sensorRepository;
    @Mock
    private TypeRepository typeRepository;


    @Test
    void testCreateSensor_shouldReturnsSensorId_assertEqualsValues() {
        SensorDto dto = new SensorDto();
        dto.setName("Temp");
        Sensor sensor = new Sensor();
        sensor.setName("Temp");
        Sensor savedSensor = new Sensor();
        savedSensor.setId(1L);

        when(sensorMapper.mapToEntity(dto)).thenReturn(sensor);
        when(sensorRepository.save(sensor)).thenReturn(savedSensor);

        Long result = sensorService.createSensor(dto);

        assertEquals(1L, result);
    }

    @Test
    void testGetById_shouldReturnSensorDto_whenSensorExists() {
        String testName = "testName";
        Long id = 1L;
        Sensor sensor = new Sensor();
        sensor.setId(id);
        sensor.setName(testName);

        SensorDto sensorDto = new SensorDto();
        sensorDto.setName(testName);

        when(sensorRepository.findById(id)).thenReturn(Optional.of(sensor));
        when(sensorMapper.mapToDto(sensor)).thenReturn(sensorDto);

        SensorDto result = sensorService.getById(id);

        assertEquals(testName, result.getName());
    }

    @Test
    void testGetById_shouldThrowException_whenSensorNotFound() {
        Long id = 99L;
        when(sensorRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            sensorService.getById(id);
        });
    }

    @Test
    void testGetAll_shouldReturnListOfSensorDto_assertEqualsCollectionSize() {
        Sensor sensor1 = new Sensor();
        sensor1.setId(1L);
        sensor1.setName("Sensor A");

        Sensor sensor2 = new Sensor();
        sensor2.setId(2L);
        sensor2.setName("Sensor B");

        List<Sensor> sensorList = Arrays.asList(sensor1, sensor2);

        SensorDto dto1 = new SensorDto();
        dto1.setName("Sensor A");

        SensorDto dto2 = new SensorDto();
        dto2.setName("Sensor B");

        List<SensorDto> dtoList = Arrays.asList(dto1, dto2);

        when(sensorRepository.findAll()).thenReturn(sensorList);
        when(sensorMapper.mapToListDto(sensorList)).thenReturn(dtoList);

        List<SensorDto> result = sensorService.getAll();

        assertEquals(2, result.size());
    }

    @Test
    void testGetByNameAndModel_shouldReturnListOfSensorDto_assertEqualsCollectionSize() {
        String name1 = "Sensor A";
        String model1 = "Model X";
        String name2 = "Sensor B";
        String model2 = "Model Y";
        String name = "Sensor";
        String model = "Model";

        Sensor sensor1 = new Sensor();
        sensor1.setId(1L);
        sensor1.setName(name1);
        sensor1.setModel(model1);

        Sensor sensor2 = new Sensor();
        sensor2.setId(2L);
        sensor2.setName(name2);
        sensor2.setModel(model2);

        List<Sensor> sensorList = Arrays.asList(sensor1, sensor2);

        SensorDto dto1 = new SensorDto();
        dto1.setName(name1);
        dto1.setModel(model1);

        SensorDto dto2 = new SensorDto();
        dto2.setName(name2);
        dto2.setModel(model2);

        List<SensorDto> dtoList = Arrays.asList(dto1, dto2);

        when(sensorRepository.findByNameAndModel(name, model)).thenReturn(sensorList);
        when(sensorMapper.mapToListDto(sensorList)).thenReturn(dtoList);

        List<SensorDto> result = sensorService.getByNameAndModel(name, model);

        assertEquals(2, result.size());
        assertEquals(name1, result.get(0).getName());
        assertEquals(model1, result.get(0).getModel());
        assertEquals(name2, result.get(1).getName());
        assertEquals(model2, result.get(1).getModel());
    }

    @Test
    void testDeleteSensor_shouldDeleteSensor_whenFound() {
        Long id = 1L;
        Sensor sensor = new Sensor();
        sensor.setId(id);

        when(sensorRepository.findById(id)).thenReturn(Optional.of(sensor));

        sensorService.deleteSensor(id);

        verify(sensorRepository, times(1)).deleteById(id);
    }

    @Test
    void testDeleteSensor_shouldThrowException_whenSensorNotFound() {
        Long id = 1L;

        when(sensorRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            sensorService.deleteSensor(id);
        });
    }

    @Test
    void testUpdateSensor_shouldReturnUpdatedSensorDto_whenSensorFound() {
        String updateSensorName = "Update Sensor";
        String oldSensorName = "Old Sensor";
        Long id = 1L;
        SensorDto sensorDto = new SensorDto();
        sensorDto.setName(updateSensorName);

        Sensor sensor = new Sensor();
        sensor.setId(id);
        sensor.setName(oldSensorName);

        Sensor updatedSensor = new Sensor();
        updatedSensor.setId(id);
        updatedSensor.setName(updateSensorName);

        SensorDto updatedSensorDto = new SensorDto();
        updatedSensorDto.setName(updateSensorName);

        when(sensorRepository.findById(id)).thenReturn(Optional.of(sensor));
        when(sensorMapper.mapToEntity(sensorDto)).thenReturn(updatedSensor);
        when(sensorRepository.save(updatedSensor)).thenReturn(updatedSensor);
        when(sensorRepository.findById(id)).thenReturn(Optional.of(updatedSensor));
        when(sensorMapper.mapToDto(updatedSensor)).thenReturn(updatedSensorDto);

        SensorDto result = sensorService.updateSensor(id, sensorDto);

        assertEquals(updateSensorName, result.getName());
    }

    @Test
    void testUpdateSensor_shouldThrowException_whenSensorNotFound() {
        Long id = 1L;
        SensorDto sensorDto = new SensorDto();
        sensorDto.setName("Updated Sensor");

        when(sensorRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            sensorService.updateSensor(id, sensorDto);
        });
    }
}