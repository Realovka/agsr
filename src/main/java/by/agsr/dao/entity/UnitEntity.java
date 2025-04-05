package by.agsr.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sensor_unit")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Unit unit;

    public UnitEntity(Long id) {
        this.id = id;
    }
}
