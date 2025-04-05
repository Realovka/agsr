package by.agsr.controller.dto;

import by.agsr.validator.annotation.MinMaxValue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@MinMaxValue(message = "Значение нижней границы диапазона должно быть меньше значения верхней границы диапазона")
public class RangeDto {

    @NotNull(message = "Значение нижней границы диапазона не может быть null")
    @Min(value = 1, message = "Значение нижней границы диапазона должна быть положительным целым числом")
    private Integer from;
    @NotNull(message = "Значение верхней границы диапазона не может быть null")
    @Min(value = 1, message = "Верхняя граница диапазона должна быть положительным целым числом")
    private Integer to;

}
