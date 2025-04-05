package by.agsr.controller.dto;

import by.agsr.dao.entity.Type;
import by.agsr.dao.entity.Unit;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SensorDto {

    @NotBlank(message = "Поле Название не должно быть пустым")
    @Size(min = 3, max = 30, message = "Поле Название должно быть от 3 до 30 символов")
    private String name;
    @NotBlank(message = "Поле Модель не должно быть пустым")
    @Size(max = 15, message = "Поле Модель должно быть не более 15 символов")
    private String model;
    @Valid
    private RangeDto rangeDto;
    @NotNull(message = "Неверное значение поля тип")
    private Type type;
    private Unit unit;
    @Size(max = 40, message = "Поде Локации должно быть не более 40 символов")
    private String location;
    @Size(max = 200, message = "Поле Описание должно быть не более 200 символов")
    private String description;

}
