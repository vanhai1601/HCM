package vn.com.mbbank.utils.validates;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = NumberFormatValidator.class
)
public @interface NumberFormat {
    String message() default "Invalid number format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
