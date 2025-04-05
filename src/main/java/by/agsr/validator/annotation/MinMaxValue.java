package by.agsr.validator.annotation;

import by.agsr.validator.MinMaxValueValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MinMaxValueValidator.class)
public @interface MinMaxValue {
    String message() default "Минимальное значение не может быть больше максимального";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
