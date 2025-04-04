package by.agsr.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

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
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "type_enum")
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private Type type;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "unit_enum")
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private Unit unit;
    private String location;
    private String description;

}
