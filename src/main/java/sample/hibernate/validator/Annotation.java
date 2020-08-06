package sample.hibernate.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = Validator.class)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Annotation {
	String message() default "";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String value() default "";

	@Documented
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.FIELD})
	@interface List {
		Annotation[] value();
	}
}
