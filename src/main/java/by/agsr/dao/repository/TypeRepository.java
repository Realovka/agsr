package by.agsr.dao.repository;

import by.agsr.dao.entity.Type;
import by.agsr.dao.entity.TypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeRepository extends JpaRepository<TypeEntity, Long> {
    Optional<TypeEntity> findByType(Type type);
}
