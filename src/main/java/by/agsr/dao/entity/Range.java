package by.agsr.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "RANGE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Range {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int rangeFrom;
    private int rangeTo;
}
