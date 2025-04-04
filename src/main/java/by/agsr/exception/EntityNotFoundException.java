package by.agsr.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class EntityNotFoundException extends RuntimeException {

    private String messageKey;
}
