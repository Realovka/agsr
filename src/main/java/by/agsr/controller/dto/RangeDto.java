package by.agsr.controller.dto;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RangeDto {
    @Positive(message = "Нижняя граница диапазона должна быть положительным числом")
    private int from;
    @Positive(message = "Верхняя граница диапазона должна быть положительным числом")
    private int to;
}
