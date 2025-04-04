package by.agsr.controller.dto;

import by.agsr.dao.entity.Type;
import by.agsr.dao.entity.Unit;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "Имя пользователя не должно быть пустым")
    @Size(min = 3, max = 50, message = "Имя пользователя должно быть от 3 до 50 символов")
    private String name;
    @NotBlank(message = "Название модели не должно быть пустым")
    @Size(max = 15, message = "Название модели должно быть не более 15 символов")
    private String model;
    private RangeDto rangeDto;
    @NotBlank
    private Type type;
    private Unit unit;
    @Size(max = 40, message = "Название локации должно быть не более 40 символов")
    private String location;
    @Size(max = 200, message = "Описание должно быть не более 200 символов")
    private String description;
}
