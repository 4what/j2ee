package sample.java;

import java.lang.annotation.*;

/**
 * TODO: java.lang.reflect.AnnotatedElement
 *
 * https://docs.oracle.com/javase/tutorial/java/annotations/
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.METHOD, ElementType.PACKAGE, ElementType.PARAMETER, ElementType.TYPE})
public @interface Annotation {
	String value() default "";
}
