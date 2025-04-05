package by.agsr.dao.repository;

import by.agsr.dao.entity.Unit;
import by.agsr.dao.entity.UnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UnitRepository extends JpaRepository<UnitEntity, Long> {
    Optional<UnitEntity> findByUnit(Unit unit);
}
