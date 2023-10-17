package com.example.demoApi.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CityFormatValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CityFormatConstraint {

    String message() default  "invalid format of the ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
