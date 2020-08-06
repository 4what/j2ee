package sample.hibernate.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class Validator implements ConstraintValidator<Annotation, String> {
	private String value;

	@Override
	public void initialize(Annotation annotation) {
		this.value = annotation.value();
		System.out.println("this.value: " + this.value);
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
		System.out.println("value: " + value);

		if (value == null) {
			return false;
		}

		if (value.contains(this.value)) {
			return false;
		}

		return true;
	}
}
