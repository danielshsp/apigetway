package com.vayusense.apigetway.errorhandler;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({FIELD, ANNOTATION_TYPE, TYPE, PARAMETER})
@Constraint(validatedBy = {AdminValidator.class})
public @interface Admin {
    String message() default "{admin}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
