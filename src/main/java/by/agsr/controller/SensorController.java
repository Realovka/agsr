package by.agsr.controller;

import by.agsr.controller.dto.SensorDto;
import by.agsr.service.SensorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sensors/v1")
public class SensorController {

    private final SensorService sensorServices;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Long createSensor(@RequestBody @Valid SensorDto sensorDto) {
        return sensorServices.createSensor(sensorDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<SensorDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(sensorServices.getById(id));
    }
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<List<SensorDto>> getAll() {
        return ResponseEntity.ok(sensorServices.getAll());
    }

    @GetMapping("/search")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<List<SensorDto>> getByNameAndModel(@RequestParam String name, @RequestParam String model) {
        return ResponseEntity.ok(sensorServices.getByNameAndModel(name, model));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteSensor(@PathVariable Long id) {
        sensorServices.deleteSensor(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<SensorDto> updateSensor(@PathVariable Long id, @RequestBody @Valid SensorDto sensorDto) {
        return ResponseEntity.ok(sensorServices.updateSensor(id, sensorDto));
    }
}
