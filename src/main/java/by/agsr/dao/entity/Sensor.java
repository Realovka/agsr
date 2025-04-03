package by.agsr.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SENSOR")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String model;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "type_enum")
    private Type type;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "unit_enum")
    private Unit unit;
    private String location;
    private String description;

}
