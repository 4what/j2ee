package sample.spring.validator;

import sample.spring.domain.Pojo;

import org.springframework.validation.*;

import java.util.Arrays;

public class Validator implements org.springframework.validation.Validator {
	@Override
	public boolean supports(Class<?> aClass) {
		return Pojo.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		Pojo pojo = (Pojo) o;

		if (pojo.getId() <= 0) {
			errors.rejectValue("id", "min", new Object[]{pojo.getId()}, "");
		}

		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "empty");
	}


	public static void main(String[] args) {
		Pojo pojo = new Pojo();

		DataBinder binder = new DataBinder(pojo);
		binder.setValidator(new Validator());
		binder.validate();

		BindingResult result = binder.getBindingResult();
		for (FieldError error : result.getFieldErrors()) {
			System.out.println("error.getDefaultMessage(): " + error.getDefaultMessage());

			System.out.println("error.getArguments(): " + Arrays.toString(error.getArguments()));
			System.out.println("error.getCode(): " + error.getCode());
			System.out.println("error.getField(): " + error.getField());
			System.out.println("error.getObjectName(): " + error.getObjectName());

			System.out.println("error.getRejectedValue(): " + error.getRejectedValue());
		}
	}
}
