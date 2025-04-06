package by.agsr.dao.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "range_id", referencedColumnName = "id")
    private Range range;
    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private TypeEntity typeEntity;
    @ManyToOne
    @JoinColumn(name = "unit_id", referencedColumnName = "id")
    private UnitEntity unitEntity;
    private String location;
    private String description;
}
