package by.agsr.dao.repository;

import by.agsr.dao.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
    @Query(value = "SELECT * FROM sensor WHERE name LIKE %:name% AND model LIKE %:model%" , nativeQuery = true)
    List<Sensor> findByNameAndModel(@Param("name") String name, @Param("model") String model);
}
