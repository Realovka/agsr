package by.agsr.validator;

import by.agsr.controller.dto.RangeDto;
import by.agsr.validator.annotation.MinMaxValue;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MinMaxValueValidator implements ConstraintValidator<MinMaxValue, RangeDto> {

    @Override
    public boolean isValid(RangeDto rangeDto, ConstraintValidatorContext context) {
        try {
            Integer from = rangeDto.getFrom();
            Integer to = rangeDto.getTo();

            return from < to;
        } catch (Exception e) {
            return false;
        }
    }
}
