package diplomski.autoceste.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OibValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OibConstraints {
    String message() default "Sorry, oib not correct!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
